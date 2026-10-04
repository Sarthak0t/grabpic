package com.grabpic.grabpic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")  //user is reserved keyword in Postgres. (edited later)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //edited later when error faced in Postman POST req.
    private Long id;
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String email;
    @Size(min=8)
    @NotBlank
    private String password;
    @Column(name = "created_at")  //here we have to use this because the variable name is different.

    @CreationTimestamp   //edited later coz database query has something which is throwing error(it tells hibernate to automatically put the current date and time).
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    protected User() {

    }
}
