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
public class KingBlack implements Figure {
    private final Cell position;

    public KingBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        int deltaY = Math.abs(source.y - dest.y);
        int deltaX = Math.abs(source.x - dest.x);
        Cell[] steps;
        if ((deltaY == 0 && deltaX == 1) || (deltaX == 0 && deltaY == 1) || (deltaY == 1 && deltaX == 1)) {
                steps = new Cell[]{dest};
        } else {
            throw new ImpossibleMoveException("Нарушение логики хода фигуры");
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }
}