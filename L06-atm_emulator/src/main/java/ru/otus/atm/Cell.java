package ru.otus.atm;

public class Cell implements Comparable<Cell> {
    private int nominal;
    private int count;

    public Cell(int nominal) {
        if(nominal == 0) {
            throw new IllegalArgumentException("There can't be cells with a nominal of zero.");
        }
        if(nominal < 0) {
            throw new IllegalArgumentException("There can't be cells with negative nominal.");
        }
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }

    public void put() {
        count = count + 1;
    }

    public int giveOutAvailableAmount(int amount) {
        int saldo = getAvailableAmount(amount);
        int countToTake = amount / nominal;
        correctCount(countToTake);
        return saldo;
    }

    public int getAvailableAmount(int amount) {
        int countToTake = amount / nominal;
        if(countToTake >= count) {
            countToTake = count;
        }
        int amountToTake = countToTake * nominal;
        return amount - amountToTake;
    }

    private void correctCount(int countToTake) {
        count = (countToTake < count) ? (count - countToTake) : 0;
    }

    public int giveOutBalance() {
        int saldo = count * nominal;
        count = 0;
        return saldo;
    }

    @Override
    public int compareTo(Cell anotherCell) {
        return (this.nominal > anotherCell.nominal) ? -1 : ((this.nominal == anotherCell.nominal) ? 0 : 1);
    }

    @Override
    public String toString() {
        return nominal + ": " + count + " bill";
    }
}
