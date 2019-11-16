package ru.otus.aop;

public class Square implements Figure {
    private int side;
    private String color;

    @Override
    public void initSquare( int side, String color ) {
        this.side = side;
        this.color = color;
    }

    @Override
    public int getArea() {
        return side * side;
    }

    @Override
    public String getColor() {
        return color;
    }
}