package com.example.nefix.blockedaccount;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BlockedAccountRequestDto
{
    private boolean isPermanent;
    private LocalDate dateOfExpire; // Only relevant if not permanent
}
