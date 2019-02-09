package app.enums;

public enum  UserRole {

    USER, ADMIN;

    @Override
    public String toString() {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }
}
