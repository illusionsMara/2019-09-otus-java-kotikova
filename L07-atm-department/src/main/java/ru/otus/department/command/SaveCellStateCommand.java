package ru.otus.department.command;

import ru.otus.department.Cell;

public class SaveCellStateCommand implements Command<Cell, Void> {

    @Override
    public Void execute( Cell cell ) {
        cell.save();
        return null;
    }
}
