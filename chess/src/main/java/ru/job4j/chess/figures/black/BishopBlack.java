package ru.job4j.chess.figures.black;
import ru.job4j.chess.Logic;
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
        boolean wrongMove = !(Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y));
        int sizeSteps = Math.abs(source.x - dest.x);
        return Logic.commonPart(source, dest, wrongMove, sizeSteps);
    }
    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}