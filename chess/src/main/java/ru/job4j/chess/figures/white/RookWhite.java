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
public class RookWhite implements Figure {
    private final Cell position;

    public RookWhite(final Cell position) {
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
        int stepX = source.x - dest.x;
        int stepY = source.y - dest.y;
        int moveX = source.x;
        int moveY = source.y;
        Cell[] steps;
            if (deltaY == 0 || deltaX == 0) {
                steps = new Cell[deltaX + deltaY];
                for (int i = 0; i < steps.length; i++) {
                    //влево
                    if (stepX > 0) {
                        steps[i] = Cell.values()[--(moveX) * 8 + moveY];
                    }
                    //вверх
                    if (stepY < 0) {
                        steps[i] = Cell.values()[moveX * 8 + (++(moveY))];
                    }
                    //вправо
                    if (stepX < 0) {
                        steps[i] = Cell.values()[++(moveX) * 8 + moveY];
                    }
                    //вниз
                    if (stepY > 0) {
                        steps[i] = Cell.values()[moveX * 8 + (--(moveY))];
                    }
                }
            } else {
                throw new ImpossibleMoveException("Нарушение логики хода фигуры");
            }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}