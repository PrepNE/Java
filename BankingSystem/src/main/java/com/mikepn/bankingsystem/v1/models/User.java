package com.mikepn.bankingsystem.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mikepn.bankingsystem.v1.enums.EAccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "users")
@SuperBuilder
public class User extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    private EAccountStatus status = EAccountStatus.ACTIVE;

    private boolean isVerified = false;

    @JsonIgnore
    private String passwordResetCode;

    @JsonIgnore
    private LocalDateTime passwordResetCodeGeneratedAt;

    @JsonIgnore
    private String verificationCode;

    @JsonIgnore
    private LocalDateTime verificationCodeGeneratedAt;

    @Transient
    private String fullName;

    public User() {}

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private Set<Role> roles;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
