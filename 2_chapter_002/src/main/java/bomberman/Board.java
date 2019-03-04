package bomberman;

import bomberman.characters.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board implements Movable {
    private final ReentrantLock[][] board;
    private final List<Thread> threads;
    private final List<Hero> characters;
    private final int size;
    private boolean stopShowBoard;
    /**
     * Конструктор. Инициализация полей.
     * @param size - размер доски
     * @param characters - список персонажей
     */
    public Board(final int size, final List<Hero> characters) {
        if (size < 5) {
            this.size = 5;
        } else {
            this.size = size;
        }
        this.board = new ReentrantLock[this.size][this.size];
        this.threads = new ArrayList<>();
        this.characters = characters;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
        for (int i = 0; i < size / 5; i++) {
            try {
                System.out.println("Добавление БЛОКОВ в конструкторе:");

                Cell freeCell = getFreeCell();
                this.board[freeCell.getX()][freeCell.getY()].tryLock();
                System.out.println(" блок поставлен на клетку [" + freeCell.getX() + "][" + freeCell.getY() + "] - "
                        + this.board[freeCell.getX()][freeCell.getY()].isLocked());

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("----------------------------constructor_FINISH----------------------------------------");
    }
    /**
     * Получение двумерного массива.
     */
    public void getStateOfBoard(int millis) {
        stopShowBoard = false;
        Thread showBoard = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(millis);
                    if (stopShowBoard) {
                        Thread.currentThread().interrupt();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                for (int i = 0; i < this.size; i++) {
                    for (int j = 0; j < this.size; j++) {
                        if (this.board[i][j].isLocked()) {
                            System.out.print(" 0 ");
                        } else {
                            System.out.print(" - ");
                        }
                    }
                    System.out.println();
                }
                System.out.println();
            }
        });
        showBoard.start();
    }
    /**
     * Проверка блокировки клетки.
     * @param dest - клетка
     * @return - истина/ложь
     */
    private boolean cellIsLock(Cell dest) {
        return this.board[dest.getX()][dest.getY()].isLocked();
    }
    /**
     * Получение свободной клетки.
     * @return - свободная клетка
     */
    private Cell getFreeCell() throws InterruptedException {
        int i, j;
        do {
            i = new Random().nextInt(this.board.length);
            j = new Random().nextInt(this.board.length);
        }
        while(this.board[i][j].isLocked());
        return new Cell(i, j);
    }
    /**
     * Попытка блокировки следующей клетки и разблокировки текущей.
     * @param source - текущая клетка
     * @param dest - следующая клетка
     * @return - новая клетка source для hero
     */
    @Override
    public Cell move(Cell source, Cell dest) throws InterruptedException {
        try {
            this.board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS);
        } finally {
            this.board[source.getX()][source.getY()].unlock();
        }
        return dest;
    }
    /**
     * Изменение следующей клетки на произвольную
     * @return - клетка
     */
    private Cell autoDest(Cell source) {
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
        //      System.out.println("dest = " + dest.toString());
        return dest;
    }
    /**
     * Изменение следующей клетки на вводимую пользователем
     * @return - dest клетка
     */
    private Cell userDest(Cell source, int step) {
        Cell newDest = new Cell(source.getX(), source.getY());
        switch (step) {
            case 2:
                newDest.setX(newDest.getX() + 1);
                break;
            case 8:
                newDest.setX(newDest.getX() - 1);
                break;
            case 4:
                newDest.setY(newDest.getY() - 1);
                break;
            case 6:
                newDest.setY(newDest.getY() + 1);
                break;
            default:
                System.out.println("Направление некорреткно!");
                break;
        }
        return newDest;
    }
    /**
     * Добавление потоков в список потоков и их запуск.
     */
    public void runGame() {
        for (Hero hero : this.characters) {
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
            this.threads.add(
                    new Thread(() -> {
                        while (this.board[hero.getCell().getX()][hero.getCell().getY()].isLocked()) {
                            try {
                                hero.setCell(getFreeCell());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }
                        this.board[hero.getCell().getX()][hero.getCell().getY()].tryLock();
                        try {
                            while (!Thread.currentThread().isInterrupted()) {
                                Thread.sleep(500);
                                Cell newSource;
                                Cell dest;
                                if ("Bomberman".equals(hero.getName())) {
                                    boolean result;
                                    do {
                                        System.out.println("Введи направление:");
                                        Scanner in = new Scanner(System.in);
                                        dest = userDest(hero.getCell(), in.nextInt());
                                        result = Movable.wayIsRight(hero.getCell(), dest, this.size)
                                                && !this.board[dest.getX()][dest.getY()].isLocked();
                                    }
                                    while (!result);
                                } else {
                                    boolean result;
                                    dest = this.autoDest(hero.getCell());
                                    do {
                                        result = Movable.wayIsRight(hero.getCell(), dest, this.size)
                                                && !this.board[dest.getX()][dest.getY()].isLocked();
                                        if (!result) {
                                            dest = this.autoDest(hero.getCell());
                                        }
                                    }
                                    while (!result);
                                }
                                newSource = move(hero.getCell(), dest);
                                // System.out.println("new Source Cell для " + hero.getName() + " = " + newSource);
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
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.start();
        });
    }
    /**
     * Прерывание потоков.
     */
    public void stopGame() {
        stopShowBoard = true;
        this.threads.forEach(Thread::interrupt);
    }

    public static void main(String[] args) {
        Hero bomberman = new Bomberman(new Cell(0, 0));
        Hero monster = new Monster(new Cell(0, 0));
        List<Hero> characters = new ArrayList<Hero>();
        characters.add(bomberman);
        characters.add(monster);
        Board board = new Board(10, characters);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board.runGame();
        //запуск отображения состояния массива
        //параметр 1 - время задержки отображения
        //параметр 2 - время показа
        board.getStateOfBoard(400);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board.stopGame();
    }
}