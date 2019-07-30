package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;
    private final int size;

    public Logic3T(Figure3T[][] table, int size) {
        this.table = table;
        this.size = size;
    }

    /**
     * Проверяет победу Х
     *
     * @return да/нет.
     */
    public boolean isWinnerX() {
        boolean[][] winCoombination = new boolean[this.size][this.size];
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                winCoombination[i][j] = this.table[i][j].hasMarkX();
            }
        }
        return this.winnerCheck(winCoombination);
    }

    /**
     * Проверяет победу 0
     *
     * @return да/нет.
     */
    public boolean isWinnerO() {
        boolean[][] winCoombination = new boolean[this.size][this.size];
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                winCoombination[i][j] = this.table[i][j].hasMarkO();
            }
        }
        return this.winnerCheck(winCoombination);
    }

    /**
     * Проверка победной коомбинации в двумерном массиве.
     *
     * @param winCoombination двумерный массив с коомбинациями.
     * @return да/нет.
     */
    private boolean winnerCheck(boolean[][] winCoombination) {
        boolean result = true;
        for (boolean[] array : winCoombination) {
            result = this.checkLine(array);
            if (result) {
                break;
            }
        }
        if (!result) {
            for (int i = 0; i < winCoombination.length; i++) {
                boolean[] temp = new boolean[this.size];
                for (int j = 0; j < winCoombination.length; j++) {
                    temp[j] = winCoombination[j][i];
                }
                result = this.checkLine(temp);
                if (result) {
                    break;
                }
            }
        }
        if (!result) {
            boolean[] temp = new boolean[this.size];
            for (int i = 0; i < winCoombination.length; i++) {
                temp[i] = winCoombination[i][i];
            }
            result = this.checkLine(temp);
        }
        if (!result) {
            boolean[] temp = new boolean[this.size];
            for (int i = 0; i < winCoombination.length; i++) {
                temp[i] = winCoombination[i][winCoombination.length - i - 1];
            }
            result = this.checkLine(temp);
        }
        return result;
    }

    /**
     * Проверяет линию на истиннность
     *
     * @param toCheck строка/столбец/диагонали.
     * @return да/нет.
     */
    private boolean checkLine(boolean[] toCheck) {
        boolean result = true;
        for (boolean cell : toCheck) {
            if (!cell) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean hasGap() {
        boolean result = false;
        for (Figure3T[] row : this.table) {
            for (Figure3T cell : row) {
                if (!cell.hasMarkX() && !cell.hasMarkO()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}