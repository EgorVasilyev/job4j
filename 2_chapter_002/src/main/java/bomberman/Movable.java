package bomberman;

public interface Movable {
    static boolean wayIsRight(Cell source, Cell dest, int size) {
        boolean result = true;
        if (dest.getX() < 0 || dest.getX() >= size || dest.getY() < 0 || dest.getY() >= size) {
            result = false;
        }
        if (source.equals(dest)
                || Math.abs(source.getX() - dest.getX()) == Math.abs(source.getY() - dest.getY())
                || Math.abs(source.getX() - dest.getX()) > 1
                || Math.abs(source.getY() - dest.getY()) > 1) {
            result = false;
        }
        return result;
    }

    boolean move(Cell source, Cell dest) throws InterruptedException;
}
