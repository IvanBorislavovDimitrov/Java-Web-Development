package app.model.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserServiceModel {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    @Pattern(regexp = "^[A-Za-z][A-Za-z.0-9]+@([A-Za-z]+(\\.)){1,}[A-Za-z0-9]+$")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
