package ru.otus.department.atm;

public interface Atm {

    int withdrawAll();

    void save();

    void undo();

    void restore();

    void setBalance(int balance);

}
