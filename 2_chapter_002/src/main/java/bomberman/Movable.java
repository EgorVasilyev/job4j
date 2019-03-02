package bomberman;

public interface Movable {
    static boolean wayIsRight(Cell source, Cell dest, int size) {
        boolean result = true;
        if (dest.getX() < 0 || dest.getX() >= size || dest.getY() < 0 || dest.getY() >= size) {
            result = false;
            //throw new IllegalMoveException("It is imposible to move outside of board!");
        }
        if (source.equals(dest)
                || Math.abs(source.getX() - dest.getX()) == Math.abs(source.getY() - dest.getY())
                || Math.abs(source.getX() - dest.getX()) > 1
                || Math.abs(source.getY() - dest.getY()) > 1) {
            result = false;
            //throw new IllegalMoveException("Move is imposible!");
        }
        return result;
    }

    Cell move(Cell source, Cell dest, int sizeOfBoard) throws InterruptedException;

    /*class IllegalMoveException extends RuntimeException {
        public IllegalMoveException(String message) {
            super(message);
        }
    }*/
}
