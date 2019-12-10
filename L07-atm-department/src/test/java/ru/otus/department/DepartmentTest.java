package ru.otus.department;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.department.atm.Atm;
import ru.otus.department.atm.AtmFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.department.enumeration.Bank.*;
import static ru.otus.department.enumeration.Nominal.*;

public class DepartmentTest {
    private Department department;
    private Atm tinkoffAtm;
    private Atm sberbankAtm;
    private Atm alfabankAtm;
    private int tinkoffBalance = 260;
    private int sberbankBalance = 280;
    private int alfabankBalance = 170;

    @BeforeEach
    private void beforeEach() {
        department = createDepartment();
    }

    @AfterEach
    private void afterEach() {
        department = null;
    }

    private Atm createTinkoffAtm() {
        tinkoffAtm = AtmFactory.create(TINKOFF);
        // кладем в банкомат купюры: =260
        tinkoffAtm.acceptAll(
                NOMINAL_50,
                NOMINAL_100,
                NOMINAL_50,
                NOMINAL_50,
                NOMINAL_10);
        return tinkoffAtm;
    }

    private Atm createSberbankAtm() {
        sberbankAtm = AtmFactory.create(SBERBANK);
        // кладем в банкомат купюры: =280
        sberbankAtm.acceptAll(
                NOMINAL_50,
                NOMINAL_100,
                NOMINAL_10,
                NOMINAL_100,
                NOMINAL_10,
                NOMINAL_10);
        return sberbankAtm;
    }

    private Atm createAlfabankAtm() {
        alfabankAtm = AtmFactory.create(ALFABANK);
        // кладем в банкомат купюры: =170
        alfabankAtm.acceptAll(
                NOMINAL_50,
                NOMINAL_50,
                NOMINAL_50,
                NOMINAL_10,
                NOMINAL_10);
        return alfabankAtm;
    }

    private Department createDepartment() {
        Department department = new Department();
        department.addAtm(createTinkoffAtm());
        department.addAtm(createSberbankAtm());
        department.addAtm(createAlfabankAtm());
        return department;
    }

    @Test
    @DisplayName("Собрать сумму остатков со всех ATM")
    public void giveOutBalanceTest() {
        int expectedSaldo = tinkoffBalance + sberbankBalance + alfabankBalance;
        assertAll(
                () -> assertEquals(expectedSaldo, department.giveOutBalance(), "Сумма остатка должна быть равна суммам, положенным в банкоматы"),
                () -> assertEquals(0, department.getBalance(), "Во всех банкоматах баланс должен стать =0")
        );
    }

    @Test
    @DisplayName("Восстановить состояние всех ATM до начального")
    public void restoreAtmStateTest() {
        tinkoffAtm.save();
        sberbankAtm.save();
        alfabankAtm.save();
        tinkoffAtm.acceptAll(NOMINAL_10, NOMINAL_50);
        sberbankAtm.acceptAll(NOMINAL_100, NOMINAL_100, NOMINAL_10);
        alfabankAtm.acceptAll(NOMINAL_10, NOMINAL_100, NOMINAL_10);
        tinkoffAtm.save();
        sberbankAtm.save();
        alfabankAtm.save();
        department.restoreAtmState();
        assertAll( "Сумма должна быть равна начальной",
                () -> assertEquals(tinkoffBalance, tinkoffAtm.getBalance()),
                () -> assertEquals(sberbankBalance, sberbankAtm.getBalance()),
                () -> assertEquals(alfabankBalance, alfabankAtm.getBalance())
        );
    }
}
