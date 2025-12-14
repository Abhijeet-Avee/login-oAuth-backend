package com.login.auth.auth_app.entity;

import com.login.auth.auth_app.enums.Provider;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // 1. JPA requires a no-args constructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    private UUID id;

    // 2. Explicitly set nullable = false for integrity
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // 3. Increased length to 255 to support all hash types (BCrypt, Argon2, Scrypt)
    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    // 4. @Builder.Default ensures the Builder uses this value instead of 0/false
    @Builder.Default
    @Column(name = "user_enabled", nullable = false)
    private boolean userEnabled = true;

    // 5. Prevent updates to creation time
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    @Builder.Default // Ensures Builder defaults to LOCAL
    @Column(name = "provider", nullable = false, length = 50)
    private Provider provider = Provider.LOCAL;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default // Ensures Builder creates an empty Set, not null
    private Set<Role> roles = new HashSet<>();

    // 6. Simplified Audit Logic
    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    // 7. Proper Hibernate-safe Equals and HashCode
    // We strictly compare by ID for entities.
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}