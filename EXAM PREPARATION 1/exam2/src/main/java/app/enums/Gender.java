package app.enums;

public enum Gender {

    MALE, FEMALE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
