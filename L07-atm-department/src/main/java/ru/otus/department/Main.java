package ru.otus.department;

import ru.otus.department.atm.Atm;
import ru.otus.department.atm.AtmFactory;
import static ru.otus.department.Bank.*;

public class Main {
    public static void main(String[] args) {
        Department department = new Department();
        // Создаем банкоматы и добавляем их в департамент
        Atm atm1 = AtmFactory.create(TINKOFF, 100);
        Atm atm2 = AtmFactory.create(SBERBANK, 200);
        Atm atm3 = AtmFactory.create(ALFABANK, 300);
        department.addAtm(atm1);
        department.addAtm(atm2);
        department.addAtm(atm3);

        // Сохраняем начальное состояние банкоматов
        atm1.save();
        atm2.save();
        atm3.save();
        System.out.println(department.toString());

        // Устанавливаем новый баланс в банкоматах и сохраняем состояние
        atm1.setBalance(1000);
        atm2.setBalance(2000);
        atm3.setBalance(3000);
        atm1.save();
        atm2.save();
        atm3.save();
        System.out.println(department.toString());

        atm1.setBalance(10000);
        atm2.setBalance(20000);
        atm3.setBalance(30000);
        atm1.save();
        atm2.save();
        atm3.save();
        System.out.println(department.toString());

        // Департамент восстанавливает состояние всех своих банкоматов до начального
        department.restoreAtmState();
        System.out.println("-----------------------------------");
        System.out.print("-> Department restores states of its ATMs:\n" + department.toString());

        // Департамент собирает сумму остатков со всех своих банкоматов
        System.out.println("-----------------------------------");
        System.out.println("-> Department collects balances from all its ATMs:");
        System.out.println( "Balance = " + department.withdrawAll());

        // Состояние банкоматов после снятия остатков
        System.out.println("-----------------------------------");
        System.out.println("States of ATM:\n" + department.toString());
    }
}