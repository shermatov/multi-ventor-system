package com.shermatov.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shermatov.ecommerce.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "users")
public class User extends BaseEntity implements UserDetails {

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    @Column (unique = true, nullable = false)
    private String email;

    @Column (nullable = false)
    @JsonIgnore
    private String password;

    @Column (nullable = false, name = "first_name")
    private String firstName;

    @Column (nullable = false, name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
