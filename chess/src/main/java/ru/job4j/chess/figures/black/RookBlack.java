package ru.job4j.chess.figures.black;

import ru.job4j.chess.Logic;
import ru.job4j.chess.Moving;
import ru.job4j.chess.exeptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Egor Vasilyev
 * @version 3
 * @since 25/11/2018
 */
public class RookBlack implements Figure, Moving {
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
        boolean wrongMove = !(Math.abs(source.y - dest.y) == 0 || (Math.abs(source.x - dest.x) == 0));
        int sizeSteps = Math.abs(source.x - dest.x) + Math.abs(source.y - dest.y);
        return Moving.commonPart(source, dest, wrongMove, sizeSteps);
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}