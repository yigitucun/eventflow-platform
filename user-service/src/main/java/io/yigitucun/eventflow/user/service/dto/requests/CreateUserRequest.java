package io.yigitucun.eventflow.user.service.dto.requests;

import io.yigitucun.eventflow.user.service.annotation.abstracts.UniqueEmail;
import io.yigitucun.eventflow.user.service.annotation.abstracts.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CreateUserRequest {
    @NotBlank
    @UniqueUsername
    private String username;
    @NotBlank
    @UniqueEmail
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    @Length(min = 6,message = "Must be at least 6 characters")
    private String password;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
