package com.example.nefix.authentification.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.nefix.authentification.user.Permission.*;

@RequiredArgsConstructor
public enum Role
{
    SENIOR(Set.of(
            SENIOR_READ,
            SENIOR_CREATE,
            SENIOR_UPDATE,
            SENIOR_DELETE,
            MEDIOR_READ,
            MEDIOR_CREATE,
            MEDIOR_UPDATE,
            MEDIOR_DELETE)
    ),
    USER(Collections.emptySet()),
    MEDIOR(Set.of(
            MEDIOR_READ,
            MEDIOR_CREATE,
            MEDIOR_UPDATE,
            MEDIOR_DELETE)
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities()
    {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
