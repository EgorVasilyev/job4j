package ru.job4j;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.Logic;
import ru.job4j.chess.figures.black.BishopBlack;
import ru.job4j.chess.figures.black.KnightBlack;
import ru.job4j.chess.figures.black.PawnBlack;
import ru.job4j.chess.figures.black.QeenBlack;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LogicTest {
    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenFreeWayForBishopBlackAnd4Moves() {
        Logic logic = new Logic();

        //вправо-вверх
        Cell source = Cell.C8;
        BishopBlack bishopBlack = new BishopBlack(source);
        logic.add(bishopBlack);
        Cell dest = Cell.G4;
        boolean result = logic.move(source, dest);
        assertTrue(result);

       //влево-вверх
        source = dest;
        dest = Cell.E2;
        result = logic.move(source, dest);
        assertTrue(result);

        //влево-вниз
        source = dest;
        dest = Cell.A6;
        result = logic.move(source, dest);
        assertTrue(result);

        //вправо-вверх
        source = dest;
        dest = Cell.E2;
        result = logic.move(source, dest);
        assertTrue(result);
    }

    @Test
    public void whenOcupiedWayForQueenBlack() {
        Logic logic = new Logic();

        Cell start = Cell.E7;
        PawnBlack pawnBlack = new PawnBlack(start);
        logic.add(pawnBlack);
        //вверх
        Cell source = Cell.E8;
        QeenBlack qeenBlack = new QeenBlack(source);
        logic.add(qeenBlack);
        Cell dest = Cell.E4;
        boolean result = logic.move(source, dest);

        assertTrue(!result);
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Путь занят!")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenWrongWayForKnightBlack() {
        Logic logic = new Logic();

        Cell source = Cell.B8;
        KnightBlack knightBlack = new KnightBlack(source);
        logic.add(knightBlack);
        Cell dest = Cell.B6;
        boolean result = logic.move(source, dest);

        assertTrue(!result);
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Нарушение логики хода фигуры!")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
