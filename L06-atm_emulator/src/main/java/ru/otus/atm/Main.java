package ru.otus.atm;

import ru.otus.atm.exception.AtmException;
import static ru.otus.atm.Nominal.*;

public class Main {
    public static void main( String[] args ) throws Exception {
        // инициализируем банкомат
        Atm atm = new Atm();
        // кладем в банкомат купюры
        atm.acceptAll(
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
        // состояние банкомата
        System.out.println(atm.toString());
        // снимаем некоторую сумму
        int amount = 350;
        try {
            atm.giveOutAmount(amount);
            System.out.println("=========================");
            System.out.println("Give out amount: " + amount);
            System.out.println("=========================");
            // состояние банкомата
            System.out.println(atm.toString());
        } catch (AtmException e) {
            System.out.println("Amount = " + amount + " cannot be issued");
        }
        // снимаем остаток денежных средств
        int saldo = atm.giveOutBalance();
        System.out.println("=========================");
        System.out.println("Cash balance issued: " + saldo);
        System.out.println("=========================");
        // состояние банкомата
        System.out.println(atm.toString());
    }
}
