package ru.otus.department.atm;

import ru.otus.department.enumeration.Nominal;
import ru.otus.department.exception.AtmException;

public interface Atm {

    void accept(Nominal nominal);

    void acceptAll(Nominal... nominals);

    void giveOutAmount(int amount) throws AtmException;

    int getBalance();

    int giveOutBalance();

    void save();

    void restore();

}
