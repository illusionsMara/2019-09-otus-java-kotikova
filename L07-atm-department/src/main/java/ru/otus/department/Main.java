package ru.otus.department;

import ru.otus.department.atm.Atm;
import ru.otus.department.atm.AtmFactory;
import ru.otus.department.exception.AtmException;

import static ru.otus.department.enumeration.Bank.*;
import static ru.otus.department.enumeration.Nominal.*;

public class Main {
    public static void main(String[] args) {
        // инициализируем банкоматы Тинькова и Сбербанка
        Atm tfAtm = AtmFactory.create(TINKOFF);
        Atm sbAtm = AtmFactory.create(SBERBANK);
        // кладем в банкоматы купюры
        tfAtm.acceptAll(
                NOMINAL_50,
                NOMINAL_100,
                NOMINAL_50,
                NOMINAL_50,
                NOMINAL_10,
                NOMINAL_10,
                NOMINAL_10,
                NOMINAL_100,
                NOMINAL_10,
                NOMINAL_10);
        sbAtm.acceptAll(
                NOMINAL_50,
                NOMINAL_100,
                NOMINAL_10,
                NOMINAL_100,
                NOMINAL_10,
                NOMINAL_10);
        // сохраняем начальное состояние банкоматов
        tfAtm.save();
        sbAtm.save();
        // создаем департамент и добавляем в него банкоматы
        Department department = new Department();
        department.addAtm(tfAtm);
        department.addAtm(sbAtm);
        System.out.println(department.toString());

        // снимаем некоторую сумму из банкомата Тинькова
        int amount = 350;
        try {
            tfAtm.giveOutAmount(amount);
            System.out.println("=========================");
            System.out.println("Give out amount from Tinkoff ATM: " + amount);
            System.out.println("=========================");
            // состояние банкомата
            System.out.println(department.toString());
        } catch (AtmException e) {
            System.out.println("Amount = " + amount + " cannot be issued");
        }
        tfAtm.save();

        // снимаем некоторую сумму из банкомата Сбербанка
        int amountsb = 150;
        try {
            sbAtm.giveOutAmount(amountsb);
            System.out.println("=========================");
            System.out.println("Give out amount from Sberbank ATM: " + amountsb);
            System.out.println("=========================");
            // состояние банкомата
            System.out.println(department.toString());
        } catch (AtmException e) {
            System.out.println("Amount = " + amountsb + " cannot be issued");
        }
        sbAtm.save();

        // снимаем остаток денежных средств
        int saldo = department.giveOutBalance();
        System.out.println("=========================");
        System.out.println("Cash balance issued: " + saldo);
        System.out.println("=========================");
        // состояние банкомата
        System.out.println(department.toString());

        // восстановливаем состояние всех ATM до начального
        department.restoreAtmState();
        System.out.println("-----------------------------------");
        System.out.println("States of ATMs:\n" + department.toString());
    }
}
