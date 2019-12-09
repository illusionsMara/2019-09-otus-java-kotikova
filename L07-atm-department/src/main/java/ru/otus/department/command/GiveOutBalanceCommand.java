package ru.otus.department.command;

import ru.otus.department.atm.Atm;

public class GiveOutBalanceCommand implements Command<Atm, Integer> {

    @Override
    public Integer execute( Atm atm ) {
        return atm.giveOutBalance();
    }

}