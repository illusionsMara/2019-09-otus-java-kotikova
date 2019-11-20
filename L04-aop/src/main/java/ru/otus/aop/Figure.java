package ru.otus.aop;

import static ru.otus.aop.LoggedTarget.PARAMS;
import static ru.otus.aop.LoggedTarget.RETURN;

public interface Figure {

    @Log(loggedTarget = {PARAMS, RETURN})
    public void initSquare(int side, String color);

    @Log(loggedTarget = {RETURN})
    public int getPerimeter();

    public int getArea();
}
