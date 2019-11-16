package ru.otus.aop;

public interface Figure {

    @Log(loggedTarget = {LoggedTarget.PARAMS, LoggedTarget.RETURN})
    public void initSquare(int side, String color);

    @Log(loggedTarget = {LoggedTarget.RETURN})
    public int getArea();

    public String getColor();
}
