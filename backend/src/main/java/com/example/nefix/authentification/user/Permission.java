package com.example.nefix.authentification.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission
{
    SENIOR_READ("senior:read"),
    SENIOR_UPDATE("senior:update"),
    SENIOR_CREATE("senior:create"),
    SENIOR_DELETE("senior:delete"),

    MEDIOR_READ("medior:read"),
    MEDIOR_UPDATE("medior:update"),
    MEDIOR_CREATE("medior:create"),
    MEDIOR_DELETE("medior:delete");

    @Getter
    private final String permission;
}
