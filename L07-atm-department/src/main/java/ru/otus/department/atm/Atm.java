package ru.otus.department.atm;

import ru.otus.department.enumeration.Nominal;
import ru.otus.department.exception.AtmException;

public interface Atm {

    /**
     * Принять банкноту указанного номинала и положить в соответствующую ячейку
     * @param nominal номинал банкноты
     */
    void accept(Nominal nominal);

    /**
     * Принять перечень банкнот указанных номиналов и разложить по соответствующим ячейкам
     * @param nominals номиналы банкнот
     */
    void acceptAll(Nominal... nominals);

    /**
     * Выдать указанную сумму минимальным количеством банкнот
     *
     * @param amount сумма
     * @throws AtmException, если данную сумму нельзя выдать
     */
    void giveOutAmount(int amount) throws AtmException;

    /**
     * Показать текущий баланс
     */
    int getBalance();

    /**
     * Выдать сумму остатка денежных средств
     */
    int giveOutBalance();

    /**
     * Сохранить состояние банкомата
     */
    void save();

    /**
     * Восстановить первоначальное состояние банкомата
     */
    void restore();

}
