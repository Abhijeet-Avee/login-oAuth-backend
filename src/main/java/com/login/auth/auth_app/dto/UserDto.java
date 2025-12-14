package com.login.auth.auth_app.dto;

import com.login.auth.auth_app.entity.Role;
import com.login.auth.auth_app.enums.Provider;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private UUID id;
    private String email;
    private String name;
    private String password;
    private String imageUrl;
    private boolean userEnabled;
    private Instant createdAt;
    private Instant updatedAt;
    private Provider provider;
    private Set<RoleDto> roles;

}
