package bomberman.characters;

import bomberman.Cell;

public abstract class Hero {
    private final String name;
    private Cell cell;

    public Hero(final String name, final Cell cell) {
        this.name = name;
        this.cell = cell;
    }

    public String getName() {
        return name;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

}