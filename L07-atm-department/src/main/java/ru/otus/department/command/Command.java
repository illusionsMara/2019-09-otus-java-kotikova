package ru.otus.department.command;

public interface Command<T, R> {

    R execute(T t);

}