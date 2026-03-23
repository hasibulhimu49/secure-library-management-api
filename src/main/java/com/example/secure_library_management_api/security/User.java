package com.example.secure_library_management_api.security;

import com.example.secure_library_management_api.model.enumm.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_table")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name = "assigning role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
