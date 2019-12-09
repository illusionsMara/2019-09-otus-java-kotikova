package ru.otus.department.command;

import ru.otus.department.Cell;

public class RestoreCellStateCommand implements Command<Cell, Void> {

    @Override
    public Void execute( Cell cell ) {
        cell.restore();
        return null;
    }
}
