package app.model.service;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {

    private String username;

    private String email;

    private String role;

    private List<String> packages;

    private List<String> receipts;

    public UserServiceModel() {
        packages = new ArrayList<>();
        receipts = new ArrayList<>();
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

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }

    public List<String> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<String> receipts) {
        this.receipts = receipts;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
