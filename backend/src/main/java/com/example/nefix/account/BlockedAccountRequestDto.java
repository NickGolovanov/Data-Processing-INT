package com.example.nefix.account;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class BlockedAccountRequestDto
{
    private boolean isPermanent;
    private LocalDate dateOfExpire; // Only relevant if not permanent
}
