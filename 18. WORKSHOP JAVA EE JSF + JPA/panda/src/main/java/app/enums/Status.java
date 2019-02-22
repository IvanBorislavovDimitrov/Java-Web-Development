package app.enums;

public enum Status {

    PENDING, SHIPPED, DELIVERED, ACQUIRED;

    @Override
    public String toString() {
        return name().substring(0, 1) + name().substring(1);
    }
}
