package ru.otus.atm;

public class Main {
    public static void main( String[] args ) throws Exception {
        // инициализируем банкомат с указанными ячейками
        Atm atm = new Atm(new Cell(100), new Cell(50), new Cell(100), new Cell(10));

        // кладем в банкомат купюры
        atm.acceptAll(50, 100, 50, 50, 10, 10, 10, 100, 10, 10);
        // состояние банкомата
        System.out.println(atm.toString());

        // снимаем некоторую сумму
        int amount = 250;
        atm.giveOutAmount( amount );
        System.out.println("Give out amount: " + amount);
        // состояние банкомата
        System.out.println(atm.toString());

        // снимаем остаток денежных средств
        int saldo = atm.giveOutBalance();
        System.out.println("Cash balance issued: " + saldo);
        // состояние банкомата
        System.out.println(atm.toString());
    }
}
