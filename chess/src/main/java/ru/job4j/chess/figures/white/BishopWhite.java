package ru.job4j.chess.figures.white;

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
public class BishopWhite implements Figure, Moving {
    private final Cell position;

    public BishopWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        boolean wrongMove = !(Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y));
        int sizeSteps = Math.abs(source.x - dest.x);
        return Moving.commonPart(source, dest, wrongMove, sizeSteps);
    }
    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}