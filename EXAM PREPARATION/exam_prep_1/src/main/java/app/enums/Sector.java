package app.enums;

public enum Sector {

    MEDICINE, CAR, FOOD, DOMESTIC, SECURITY;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
