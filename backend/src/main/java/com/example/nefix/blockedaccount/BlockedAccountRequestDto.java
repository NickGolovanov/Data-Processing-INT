package com.example.nefix.blockedaccount;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BlockedAccountRequestDto
{
    private boolean isPermanent;
    @NotNull(message = "Date of expire cannot be empty")
    @FutureOrPresent(message = "Date of expire cannot be in the past")
    private LocalDate dateOfExpire; // Only relevant if not permanent
}
