package ru.otus.department.atm;

import ru.otus.department.Bank;

public class AtmFactory {

    public static Atm create(Bank bank, int balance) {
        switch (bank) {
            case TINKOFF:
                return new TinkoffAtm(balance);
            case SBERBANK:
                return new SberbankAtm(balance);
            case ALFABANK:
                return new AlfabankAtm(balance);
            default:
                return null;
        }
    }

}
