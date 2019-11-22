package ru.otus.atm;

public enum Nominal {
    NOMINAL_10(10),
    NOMINAL_50(50),
    NOMINAL_100(100);

    private final int value;

    private Nominal(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
