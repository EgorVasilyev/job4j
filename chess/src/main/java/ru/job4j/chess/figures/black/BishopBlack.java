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
        int deltaX = Math.abs(source.x - dest.x);
        int deltaY = Math.abs(source.y - dest.y);
        int stepX = source.x - dest.x;
        int stepY = source.y - dest.y;
        Cell[] steps;
        int moveX = source.x;
        int moveY = source.y;
        //проверяем может ли фигура так в принципе ходить
        if (deltaY == deltaX) {
            dest.setXY(dest.x, dest.y);
            steps = new Cell[deltaX];
            for (int i = 0; i < steps.length; i++) {
                //влево-вниз
                if (stepX > 0 && stepY > 0) {
                    steps[i] = Cell.values()[--(moveX) * 8 + (--(moveY))];
                }
                //влево-вверх
                if (stepX > 0 && stepY < 0) {
                    steps[i] = Cell.values()[--(moveX) * 8 + (++(moveY))];
                }
                //вправо-вниз
                if (stepX < 0 && stepY > 0) {
                    steps[i] = Cell.values()[++(moveX) * 8 + (--(moveY))];
                }
                //вправо-вверх
                if (stepX < 0 && stepY < 0) {
                    steps[i] = Cell.values()[++(moveX) * 8 + (++(moveY))];
                }
            }
        } else {
            throw new ImpossibleMoveException("Нарушение логики хода фигуры");
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}