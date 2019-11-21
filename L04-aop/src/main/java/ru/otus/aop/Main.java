package ru.otus.aop;

public class Main {

    public static void main(String... args) {
        Figure figure = IoC.createFigure();
        figure.initSquare( 5, "green" );
        System.out.println("area: " + figure.getArea() + ", perimeter: " + figure.getPerimeter());
    }
}
