package ru.otus.department;

import ru.otus.department.atm.Atm;
import ru.otus.department.command.GiveOutBalanceCommand;
import ru.otus.department.command.RestoreAtmStateCommand;

import java.util.ArrayList;
import java.util.Collection;

public class Department {
    private Collection<Atm> atms = new ArrayList<>();

    public void addAtm(Atm atm) {
        atms.add(atm);
    }

    public int getBalance() {
        return atms.stream().mapToInt(Atm::getBalance).sum();
    }

    /**
     * Собрать сумму остатков со всех ATM
     */
    public int giveOutBalance() {
        GiveOutBalanceCommand command = new GiveOutBalanceCommand();
        return atms.stream().mapToInt( command::execute ).sum();
    }

    /**
     * Восстановить состояние всех ATM до начального (начальные состояния у разных ATM могут быть разными)
     */
    public void restoreAtmState() {
        RestoreAtmStateCommand command = new RestoreAtmStateCommand();
        atms.forEach( command::execute );
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Department ATMs:\n");
        for (Atm atm: atms) {
            str.append(atm.toString()).append("\n");
        }
        return str.toString();
    }
}