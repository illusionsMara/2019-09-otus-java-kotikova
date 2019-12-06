package ru.otus.department.atm;

import java.util.ArrayDeque;
import java.util.Deque;

class TinkoffAtm implements Atm {
    private int balance;
    private Deque<Memento> history = new ArrayDeque<>();

    TinkoffAtm(int balance) {
        this.balance = balance;
    }

    @Override
    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public int withdrawAll() {
        int saldo = balance;
        System.out.println("Tinkoff ATM: withdraw " + saldo);
        balance = 0;
        return saldo;
    }

    @Override
    public void save() {
        history.push( new Memento() );
    }

    @Override
    public void undo() {
        Memento memento = history.pop();
        this.balance = memento.memBalance;
    }

    @Override
    public void restore() {
        Memento memento = history.removeLast();
        this.balance = memento.memBalance;
    }

    private final class Memento {
        private final int memBalance;

        private Memento() {
            this.memBalance = balance;
        }
    }

    @Override
    public String toString() {
        return "Tinkoff ATM: balance = " + balance;
    }
}
