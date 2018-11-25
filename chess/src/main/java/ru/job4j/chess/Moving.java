package ru.job4j.chess;

import ru.job4j.chess.exeptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;

public interface Moving {
    static Cell[] commonPart(Cell source, Cell dest, boolean wrongMove, int sizeSteps) {
        if (wrongMove) {
            throw new ImpossibleMoveException("Нарушение логики хода фигуры");
        }
        int deltaX = Integer.compare(dest.x, source.x);
        int deltaY = Integer.compare(dest.y, source.y);
        Cell[] steps = new Cell[sizeSteps];
        for (int i = 0; i < steps.length; i++) {
            steps[i] = Cell.values()[((source.x) + deltaX * (i + 1)) * 8 + (source.y + deltaY * (i + 1))];
        }
        return steps;
    }
}
