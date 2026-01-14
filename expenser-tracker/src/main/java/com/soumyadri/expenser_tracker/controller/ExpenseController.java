package com.soumyadri.expenser_tracker.controller;

import com.soumyadri.expenser_tracker.dto.CategorySummaryResponse;
import com.soumyadri.expenser_tracker.dto.ExpenseRequest;
import com.soumyadri.expenser_tracker.entity.Expense;
import com.soumyadri.expenser_tracker.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> addExpense(
            @RequestBody @Valid ExpenseRequest request
    ) {
        return ResponseEntity.ok(expenseService.addExpense(request));
    }

    @GetMapping
    public ResponseEntity<Page<Expense>> getExpenses(
            @RequestParam(required = false) String type,
            @PageableDefault(
                    page = 0,
                    size = 2,
                    sort = "expenseDate",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        if (type != null) {
            return ResponseEntity.ok(expenseService.fetchExpenseByCategory(type, pageable));
        }
        return ResponseEntity.ok(expenseService.fetchExpense(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(expenseService.fetchExpenseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> editExpenseById(
            @PathVariable Long id,
            @RequestBody @Valid ExpenseRequest request
    ) {
        return ResponseEntity.ok(expenseService.editExpenseById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenseById(
            @PathVariable Long id
    ) {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/monthly-total")
    public ResponseEntity<BigDecimal> getMonthlyTotal(
            @RequestParam int month,
            @RequestParam int year
    ) {
        return ResponseEntity.ok(expenseService.getMonthlyTotal(month, year));
    }

    @GetMapping("/category-summary")
    public ResponseEntity<List<CategorySummaryResponse>> getCategorySummary(
            @RequestParam int month,
            @RequestParam int year
    ) {
        return ResponseEntity.ok(
                expenseService.getCategoryWiseSummary(month, year)
        );
    }
}