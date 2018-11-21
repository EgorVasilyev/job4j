package ru.job4j.chess.figures.white;

import ru.job4j.chess.exeptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Egor Vasilyev
 * @version 1
 * @since 06/11/2018
 */
public class KnightWhite implements Figure {
    private final Cell position;

    public KnightWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        int stepY = Math.abs(source.y - dest.y);
        int stepX = Math.abs(source.x - dest.x);
        if (!((stepY == 2 && stepX == 1) || (stepY == 1 && stepX == 2))) {
            throw new ImpossibleMoveException("Нарушение логики хода фигуры");
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}