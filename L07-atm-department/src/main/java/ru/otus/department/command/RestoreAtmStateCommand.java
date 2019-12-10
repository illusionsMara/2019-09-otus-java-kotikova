package ru.otus.department.command;

import ru.otus.department.atm.Atm;

public class RestoreAtmStateCommand implements Command<Atm, Void> {

    @Override
    public Void execute( Atm atm ) {
        atm.restore();
        return null;
    }

}