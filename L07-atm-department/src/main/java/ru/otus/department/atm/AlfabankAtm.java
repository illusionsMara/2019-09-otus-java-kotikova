package ru.otus.department.atm;

import ru.otus.department.Cell;
import ru.otus.department.command.RestoreCellStateCommand;
import ru.otus.department.command.SaveCellStateCommand;
import ru.otus.department.enumeration.Nominal;
import ru.otus.department.exception.AtmException;

import java.util.*;

class AlfabankAtm implements Atm {
    private final Set<Cell> cells;

    AlfabankAtm() {
        this.cells = new TreeSet<>();
        Arrays.stream(Nominal.values()).forEach(nominal -> cells.add(new Cell(nominal)));
    }

    public void accept(Nominal nominal) {
        Cell cell = getCell(nominal);
        cell.put();
    }

    public void acceptAll(Nominal... nominals) {
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

    public int getBalance() {
        return cells.stream().mapToInt(Cell::getBalance).sum();
    }

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

    public int giveOutBalance() {
        return cells.stream().mapToInt(Cell::giveOutBalance).sum();
    }

    @Override
    public void save() {
        SaveCellStateCommand command = new SaveCellStateCommand();
        cells.forEach( command::execute );
    }

    @Override
    public void restore() {
        RestoreCellStateCommand command = new RestoreCellStateCommand();
        cells.forEach( command::execute );
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Alfabank ATM:\n");
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