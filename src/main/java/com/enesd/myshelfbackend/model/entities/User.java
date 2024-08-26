package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.enums.Role;
import com.enesd.myshelfbackend.model.abstracts.Auditable;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "users")
public class User extends Auditable implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", nullable = false))
    @Column(name = "role_type")
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<Friendship> friendships;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserSubscription> subscriptions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @PrePersist
    private void beforePersist() {
        Faker faker = new Faker();
        displayName = "user_" + faker.number().digits(12);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
