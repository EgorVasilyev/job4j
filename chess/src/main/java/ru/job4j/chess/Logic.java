package ru.job4j.chess;

import ru.job4j.chess.exeptions.FigureNotFoundException;
import ru.job4j.chess.exeptions.ImpossibleMoveException;
import ru.job4j.chess.exeptions.OccupiedWayException;
import ru.job4j.chess.figures.*;

/**
 * //TODO add comments.
 *
 * @author Egor Vasilyev
 * @version 1
 * @since 06/11/2018
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws OccupiedWayException,
            ImpossibleMoveException,
            FigureNotFoundException {

        boolean rst = false;
        int index = this.findBy(source);
        try {
            figureAbsent(index);
            Cell[] steps = this.figures[index].way(source, dest);
            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
            }
            if (!occupiedWay(source, dest, index)) {
                this.figures[index] = this.figures[index].copy(dest);
            }
        } catch (FigureNotFoundException fnfe) {
            rst = false;
            System.out.println("Фигура не найдена!");
        } catch (ImpossibleMoveException ime) {
            rst = false;
            System.out.println("Нарушение логики хода фигуры!");
        } catch (OccupiedWayException owe) {
            rst = false;
            System.out.println("Путь занят!");
        }
        return rst;
    }

    private void figureAbsent(int index) {
        if (index == -1) {
            throw new FigureNotFoundException("Фигура не найдена!");
        }
    }

    private boolean occupiedWay(Cell source, Cell dest, int index) {
        this.index = index;
        boolean result = false;
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);

            for (Cell step : steps) {
                for (Figure otherFigure : this.figures) {
                    if (otherFigure != null && step.equals(otherFigure.position())) {
                        result = true;
                    }
                    if (otherFigure != null && step.equals(otherFigure.position())) {
                        throw new OccupiedWayException("Путь занят!");
                    }
                }
            }
        }
        return result;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}