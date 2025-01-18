package com.example.nefix.profile;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfilePreferencesDto
{
    @NotNull(message = "Preference ID cannot be null")
    private Long preferenceId;
}
