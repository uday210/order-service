package com.example.orderservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderRequest(
        @NotBlank String customerName,
        @NotBlank String product,
        @NotNull @Min(1) Integer quantity,
        @NotNull @Positive BigDecimal price
) {}