package ru.otus.department.atm;

import ru.otus.department.enumeration.Bank;

public class AtmFactory {

    public static Atm create(Bank bank) {
        switch (bank) {
            case TINKOFF:
                return new TinkoffAtm();
            case SBERBANK:
                return new SberbankAtm();
            case ALFABANK:
                return new AlfabankAtm();
            default:
                return null;
        }
    }

}
