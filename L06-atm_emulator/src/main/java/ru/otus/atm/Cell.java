package ru.otus.atm;

public class Cell implements Comparable<Cell> {
    private Nominal nominal;
    private int count;

    public Cell(Nominal nominal) {
        if(nominal.getValue() <= 0) {
            throw new IllegalArgumentException("There can't be cells with negative or zero nominal");
        }
        this.nominal = nominal;
    }

    public Nominal getNominal() {
        return nominal;
    }

    public void put() {
        System.out.println("put banknote in the cell " + nominal.getValue());
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
        int countToTake = getCountToTake(amount);
        if(countToTake != 0) {
            System.out.println("give out " + countToTake + " banknotes with " + nominal);
        }
        count = count - countToTake;
        return saldo;
    }

    /**
     * Рассчитать остаток, который получится, если данную сумму полжить в ячейку
     *
     * @param amount сумма
     * @return  остаток суммы
     */
    public int getSaldo(int amount) {
        int countToTake = getCountToTake(amount);
        int amountToTake = countToTake * nominal.getValue();
        return amount - amountToTake;
    }

    private int getCountToTake(int amount) {
        int countToTake = amount / nominal.getValue();
        if(countToTake > count) {
            countToTake = count;
        }
        return countToTake;
    }

    /**
     * Выдать сумму остатка денежных средств ячейки
     *
     * @return размер суммы
     */
    public int giveOutBalance() {
        int saldo = count * nominal.getValue();
        count = 0;
        return saldo;
    }

    @Override
    public int compareTo(Cell anotherCell) {
        return (this.nominal.getValue() > anotherCell.nominal.getValue()) ? -1 : ((this.nominal == anotherCell.nominal) ? 0 : 1);
    }

    @Override
    public String toString() {
        return nominal + ": " + count + " banknote";
    }
}
