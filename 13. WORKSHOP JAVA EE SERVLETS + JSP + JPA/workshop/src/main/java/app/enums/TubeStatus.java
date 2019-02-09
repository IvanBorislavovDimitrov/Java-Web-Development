package app.enums;

public enum TubeStatus {

    PENDING, APPROVED, DECLINED;

    @Override
    public String toString() {
        return name().substring(0, 1) + name().substring(1);
    }
}
