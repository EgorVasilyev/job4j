package bomberman;

import bomberman.Characters.Bomberman;
import bomberman.Characters.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board implements Movable {
    private final ReentrantLock[][] board;
    private final List<Thread> threads;
    private final List<Hero> characters;
    private final int size;
    /**
     * Конструктор. Инициализация полей.
     * @param size - размер доски
     * @param characters - список персонажей
     */
    public Board(final int size, final List<Hero> characters) {
        this.size = size;
        this.board = new ReentrantLock[this.size][this.size];
        this.threads = new ArrayList<>();
        this.characters = characters;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new ReentrantLock();

                System.out.println("Клетка i=" + i + " j=" + j + " добавлена");

            }
        }
        for (Hero hero : characters) {
            if (cellIsLock(hero.getCell())
                    || hero.getCell().getX() < 0
                    || hero.getCell().getX() >= this.size
                    || hero.getCell().getY() < 0
                    || hero.getCell().getY() >= this.size) {
                System.out.println("bad Cell in constructor");
                try {
                    hero.setCell(getFreeCell());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    /**
     * Получение двумерного массива.
     */
    public ReentrantLock[][] getBoard() {
        return this.board;
    }
    /**
     * Проверка блокировки клетки.
     * @param dest - клетка
     * @return - истина/ложь
     */
    private boolean cellIsLock(Cell dest) {
        boolean result = this.board[dest.getX()][dest.getY()].isLocked();

        /*System.out.print("в методе cellIsLock ");
        System.out.
                println("клетка [" + dest.getX() + "," +dest.getY() + "] заблокирована? -" + result);
*/
        return result;
    }
    /**
     * Получение свободной клетки.
     * @return - свободная клетка
     */
    private Cell getFreeCell() throws InterruptedException {

        System.out.println("в методе getFreeCell");

        Cell result = null;
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j].tryLock()) {

                    System.out.println("Клетка x(i)=" + i + " y(j)=" + j + " заблокирована? -" + this.board[i][j].isLocked());

                    result = new Cell(i, j);
                    break;
                }
            }
        }
        if (result == null) {
            throw new InterruptedException("getFreeCell exception");
        }
        return result;
    }
    /**
     * Попытка блокировки следующей клетки и разблокировки текущей.
     * @param source - текущая клетка
     * @param dest - следующая клетка
     * @param sizeOfBoard - размер доски (двумерного массива)
     * @return - новая клетка source для hero
     */
    @Override
    public Cell move(Cell source, Cell dest, int sizeOfBoard) throws InterruptedException {

        System.out.println("---------------------------------------move_START-------------------------------------------------");

        Cell newDest = dest;

        boolean result = false;
        try {
            result = Movable.wayIsRight(source, dest, sizeOfBoard)
                    && this.board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS);

            System.out.println("result до попытки сменить dest-" + result);
            //количество попыток сменить dest
            int count = 1;
            while (!result && count < 10) {
                newDest = this.changeDest(source);
                result = Movable.wayIsRight(source, newDest, sizeOfBoard)
                        && this.board[newDest.getX()][newDest.getY()].tryLock(500, TimeUnit.MILLISECONDS);

                System.out.println("попытка №" + count);
                System.out.println("result после смены dest-" + result);
                /*System.out.println("Клетка [" + newDest.getX() + "," + newDest.getY()
                        + "] заблокирована(после tryLock)? -" + this.board[newDest.getX()][newDest.getY()].isLocked());*/
                count++;
            }
        } finally {
            if (result) {
                System.out.println("result is TRUE----->");

                System.out.println("Клетка [" + source.getX() + "," + source.getY()
                        + "] заблокирована(до unlock)? -" + this.board[source.getX()][source.getY()].isLocked());

                this.board[source.getX()][source.getY()].unlock();

                System.out.println("Клетка [" + source.getX() + "," + source.getY()
                        + "] заблокирована(после unlock)? -" + this.board[source.getX()][source.getY()].isLocked());
            }
        }
        System.out.println("---------------------------------------move_over-------------------------------------------------");
        return newDest;
    }
    /**
     * Изменение следующей клетки на произвольную
     * @return - клетка
     */
    private Cell changeDest(Cell source){

        System.out.println("в методе changeDest");

        Cell dest = new Cell(0, 0);
        switch (new Random().nextInt(4) + 1) {
            case 1:
                dest.setX(source.getX() + 1);
                dest.setY(source.getY());
                break;
            case 2:
                dest.setX(source.getX() - 1);
                dest.setY(source.getY());
                break;
            case 3:
                dest.setX(source.getX());
                dest.setY(source.getY() + 1);
                break;
            default:
                dest.setX(source.getX());
                dest.setY(source.getY() - 1);
        }
        System.out.println("dest = " + dest.toString());
        return dest;

    }
    /**
     * Добавление потоков в список потоков и их запуск.
     */
    public void runGame() {

        for (Hero hero : this.characters) {
            this.threads.add(
                    new Thread(() -> {
                        System.out.println("имя героя - " + hero.getName());

                        System.out.
                                println("ставим героя на клетку: клетка [" + hero.getCell().getX() + "," + hero.getCell().getY() + "] заблокирована(до tryLock)? -"
                                        + this.board[hero.getCell().getX()][hero.getCell().getY()].isLocked());

                        this.board[hero.getCell().getX()][hero.getCell().getY()].tryLock();

                        System.out.
                                println("ставим героя на клетку: клетка [" + hero.getCell().getX() + "," + hero.getCell().getY() + "] заблокирована(после tryLock)? -"
                                        + this.board[hero.getCell().getX()][hero.getCell().getY()].isLocked());
                        try {
                            while (!Thread.currentThread().isInterrupted()) {
                                Cell dest = this.changeDest(hero.getCell());
                                Cell newSource = move(hero.getCell(), dest, this.size);

                                System.out.println("newSource для Hero = " + newSource);
                                hero.setCell(newSource);

                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    })
            );
        }
        this.threads.forEach(thread -> {
            thread.start();
            System.out.println(thread.getName() + " is " + thread.getState());
        });
    }
    /**
     * Прерывание потоков.
     */
    public void stopGame() {
        this.threads.forEach(Thread::interrupt);
    }

    public static void main(String[] args) {
        Hero bomberman = new Bomberman(new Cell(0, 0));
        List<Hero> characters = new ArrayList<Hero>();
        characters.add(bomberman);
        Board board = new Board(10, characters);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board.runGame();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board.stopGame();
    }
}

