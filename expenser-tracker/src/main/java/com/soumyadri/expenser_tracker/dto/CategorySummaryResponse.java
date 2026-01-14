package com.soumyadri.expenser_tracker.dto;

import java.math.BigDecimal;

public record CategorySummaryResponse(
        String category,
        BigDecimal totalAmount
) { }
