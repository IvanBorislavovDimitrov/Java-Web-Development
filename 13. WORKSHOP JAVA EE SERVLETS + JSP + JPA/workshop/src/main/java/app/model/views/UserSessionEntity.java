package app.model.views;

import app.enums.UserRole;

public class UserSessionEntity {

    private String username;

    private UserRole userRole;

    public UserSessionEntity() {
    }

    public UserSessionEntity(String username, UserRole userRole) {
        this.username = username;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
