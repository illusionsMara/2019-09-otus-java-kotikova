package ru.otus.atm;

public class Cell implements Comparable<Cell> {
    private int nominal;
    private int count;

    public Cell(int nominal) {
        if(nominal <= 0) {
            throw new IllegalArgumentException("There can't be cells with negative or zero nominal");
        }
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }

    public void put() {
        System.out.println("put banknote in the cell " + nominal);
        count = count + 1;
    }

    /**
     * Выдать доступную часть указанной суммы
     *
     * @param amount сумма
     * @return  остаток суммы
     */
    public int giveOutAvailableAmount(int amount) {
        int saldo = getSaldo(amount);
        int countToTake = amount / nominal;
        correctCount(countToTake);
        return saldo;
    }

    /**
     * Рассчитать остаток, который получится, если данную сумму полжить в ячейку
     *
     * @param amount сумма
     * @return  остаток суммы
     */
    public int getSaldo(int amount) {
        int countToTake = amount / nominal;
        if(countToTake > count) {
            countToTake = count;
        }
        int amountToTake = countToTake * nominal;
        return amount - amountToTake;
    }

    private void correctCount(int countToTake) {
        count = (countToTake < count) ? (count - countToTake) : 0;
    }

    /**
     * Выдать сумму остатка денежных средств ячейки
     *
     * @return размер суммы
     */
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
        return nominal + ": " + count + " banknote";
    }
}
