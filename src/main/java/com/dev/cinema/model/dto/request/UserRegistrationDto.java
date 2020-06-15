package com.dev.cinema.model.dto.request;

import com.dev.cinema.annotation.EmailConstraint;
import com.dev.cinema.annotation.PasswordValueMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordValueMatch
public class UserRegistrationDto {
    @NotNull
    @EmailConstraint
    private String email;
    @NotNull
    @Size(min = 8, max = 32)
    private String password;
    private String repeatPassword;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
