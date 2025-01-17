package com.example.nefix.blockedaccount;

import jakarta.validation.constraints.Future;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BlockedAccountRequestDto
{
    private boolean isPermanent;

    @Future(message = "Date of expire must be in the future")
    private LocalDate dateOfExpire; // Only relevant if not permanent
}
