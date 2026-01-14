package com.soumyadri.expenser_tracker.repository;

import com.soumyadri.expenser_tracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("""
        SELECT SUM(e.amount)
        FROM Expense e
        WHERE MONTH(e.expenseDate) = :month
        AND YEAR(e.expenseDate) = :year
    """)
    BigDecimal getMonthlyTotal(int month, int year);

    @Query("""
        SELECT e.category, SUM(e.amount)
        FROM Expense e
        WHERE MONTH(e.expenseDate) = :month
        AND YEAR(e.expenseDate) = :year
        GROUP BY e.category
    """)
    List<Object[]> getCategoryWiseTotal(int month, int year);

    @Query("""
        SELECT e
        FROM Expense e
        WHERE e.category = :type
    """)
    Page<Expense> getExpenseByCategory(String type, Pageable pageable);
}