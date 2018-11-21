package ru.job4j.chess.figures.black;
import ru.job4j.chess.exeptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
/**
 *
 * @author Egor Vasilyev
 * @version 2
 * @since 21/11/2018
 */
public class BishopBlack implements Figure {
    private final Cell position;
    public BishopBlack(final Cell position) {
        this.position = position;
    }
    @Override
    public Cell position() {
        return this.position;
    }
    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!(Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y))) {
            throw new ImpossibleMoveException("Нарушение логики хода фигуры");
        }
        int deltaX = Integer.compare(dest.x, source.x);
        int deltaY = Integer.compare(dest.y, source.y);
        Cell[] steps = new Cell[Math.abs(source.x - dest.x)];
        for (int i = 0; i < steps.length; i++) {
            steps[i] = Cell.values()[((source.x) + deltaX * (i + 1)) * 8 + (source.y + deltaY * (i + 1))];
        }
        return steps;
    }
    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}