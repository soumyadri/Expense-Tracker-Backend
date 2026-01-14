package com.soumyadri.expenser_tracker.service;

import com.soumyadri.expenser_tracker.dto.CategorySummaryResponse;
import com.soumyadri.expenser_tracker.dto.ExpenseRequest;
import com.soumyadri.expenser_tracker.entity.Expense;
import com.soumyadri.expenser_tracker.exception.ExpenseNotFoundException;
import com.soumyadri.expenser_tracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public Expense addExpense(ExpenseRequest request) {
        Expense expense = Expense.builder()
                .amount(request.amount())
                .category(request.category())
                .description(request.description())
                .expenseDate(request.expenseDate())
                .build();

        return expenseRepository.save(expense);
    }

    public Page<Expense> fetchExpense(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

    public Expense fetchExpenseById(Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    public Expense editExpenseById(Long id, ExpenseRequest request) {
        Expense existingExpense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));

        existingExpense.setAmount(request.amount());
        existingExpense.setExpenseDate(request.expenseDate());
        existingExpense.setDescription(request.description());
        existingExpense.setCategory(request.category());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpenseById(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ExpenseNotFoundException(id);
        }
        expenseRepository.deleteById(id);
    }

    public Page<Expense> fetchExpenseByCategory(String type, Pageable pageable) {
        return expenseRepository.getExpenseByCategory(type, pageable);
    }

    public BigDecimal getMonthlyTotal(int month, int year) {
        return expenseRepository.getMonthlyTotal(month, year);
    }

    public List<CategorySummaryResponse> getCategoryWiseSummary(int month, int year) {
        return expenseRepository.getCategoryWiseTotal(month, year)
                .stream()
                .map(obj -> new CategorySummaryResponse(
                        (String) obj[0],
                        (BigDecimal) obj[1]
                ))
                .toList();
    }
}
