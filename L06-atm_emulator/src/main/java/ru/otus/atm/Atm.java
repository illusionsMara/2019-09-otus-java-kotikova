package ru.otus.atm;

import ru.otus.atm.exception.AtmException;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Atm {
    private final Set<Cell> cells;

    public Atm() {
        this.cells = new TreeSet<>();
        Arrays.stream(Nominal.values()).forEach(nominal -> cells.add(new Cell(nominal)));
    }

    /**
     * Принять банкноту указанного номинала и положить в соответствующую ячейку
     *
     * @param nominal номинал банкноты
     * @throws AtmException, если этот банкомат не принимает банкноты данного номинала
     */
    public void accept(Nominal nominal) throws AtmException {
        Cell cell = getCell(nominal);
        if(cell == null) {
            throw new AtmException("This ATM does't accept banknotes of " + nominal);
        } else {
            cell.put();
        }
    }

    /**
     * Принять перечень банкнот указанных номиналов и разложить по соответствующим ячейкам
     *
     * @param nominals номиналы банкнот
     * @throws AtmException, если этот банкомат не принимает банкноты данного номинала
     */
    public void acceptAll(Nominal... nominals) throws AtmException {
        for(Nominal nominal: nominals) {
            accept(nominal);
        }
    }

    private Cell getCell(Nominal nominal) {
        for(Cell cell: cells) {
            if(cell.getNominal() == nominal) {
                return cell;
            }
        }
        return null;
    }

    /**
     * Выдать указанную сумму минимальным количеством банкнот
     *
     * @param amount сумма
     * @throws AtmException, если данную сумму нельзя выдать
     */
    public void giveOutAmount(int amount) throws AtmException {
        this.tryGiveOutAmount(amount);
        for(Cell cell: cells) {
            amount = cell.giveOutAvailableAmount(amount);
        }
    }

    private void tryGiveOutAmount(int amount) throws AtmException {
        for(Cell cell: cells) {
            amount = cell.getSaldo(amount);
        }
        if(amount > 0) {
            throw new AtmException("The amount remained outstanding: " + amount);
        }
    }

    /**
     * Выдать сумму остатка денежных средств
     */
    public int giveOutBalance() {
        return cells.stream().mapToInt(Cell::giveOutBalance).sum();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if(cells != null) {
            for(Cell cell : cells) {
                if(cell != null) {
                    str.append(cell.toString()).append("\n");
                }
            }
        }
        return str.toString();
    }
}