package com.example.nefix.profile;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
public class ProfilePreferencesDto
{
    @NotNull(message = "Profile ID cannot be null")
    private Long profileId;
    @NotNull(message = "Preference ID cannot be null")
    private Long preferenceId;
}
