package ru.job4j.chess.figures.black;

import ru.job4j.chess.exeptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Egor Vasilyev
 * @version 1
 * @since 06/11/2018
 */
public class RookBlack implements Figure {
    private final Cell position;

    public RookBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!(Math.abs(source.y - dest.y) == 0 || (Math.abs(source.x - dest.x) == 0))) {
            throw new ImpossibleMoveException("Нарушение логики хода фигуры");
        }
        int deltaX = Integer.compare(dest.x, source.x);
        int deltaY = Integer.compare(dest.y, source.y);
        Cell[] steps = new Cell[Math.abs(source.x - dest.x) + Math.abs(source.y - dest.y)];
        for (int i = 0; i < steps.length; i++) {
            steps[i] = Cell.values()[((source.x) + deltaX * (i + 1)) * 8 + (source.y + deltaY * (i + 1))];
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}