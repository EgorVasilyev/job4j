package bomberman;

import bomberman.characters.Bomberman;
import bomberman.characters.Hero;
import bomberman.characters.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board implements Movable {
    private final ReentrantLock[][] board;
    private Thread threadBomberman;
    private final List<Hero> characters;
    private final int size;
    private boolean stop;
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
        this.characters = characters;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
        System.out.println("Adding blocks...");
        for (int i = 0; i < size / 5; i++) {
            try {
                Cell freeCell = getFreeCell();
                this.board[freeCell.getX()][freeCell.getY()].tryLock();
                System.out.println(" Cell-block is locked: [" + freeCell.getX() + "][" + freeCell.getY() + "] - "
                        + this.board[freeCell.getX()][freeCell.getY()].isLocked());

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("----------------------------constructor_FINISH----------------------------------------");
    }
    /**
     * Получение псевдографики-состояния двумерного массива.
     */
    public void getStateOfBoard() {
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
    public boolean move(Cell source, Cell dest) throws InterruptedException {
        return Movable.wayIsRight(source, dest, this.size)
                && this.board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS);
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
                System.out.println("Incorrect buttom!");
                break;
        }
        return newDest;
    }
    /**
     * Проверка, свободна ли клетка для героя и не находится ли она за границами доски.
     * Если условие не выполняется, то устанавливаем герою рандомную свободную клетку.
     */
    private void checkCorrectPut(Hero hero) {
        while (cellIsLock(hero.getCell())
                || hero.getCell().getX() < 0
                || hero.getCell().getX() >= this.size
                || hero.getCell().getY() < 0
                || hero.getCell().getY() >= this.size) {
            try {
                System.out.println("Incorrect cell for " + hero.getName() + ". Changing cell...");
                hero.setCell(getFreeCell());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
    /**
     * Добавление потоков в список потоков и их запуск.
     */
    public void runGame() {
        List<Thread> threadsForMonsters = new ArrayList<>();
        int numberOfMonsterThread = 0;
        for (Hero hero : this.characters) {
            if ("Bomberman".equals(hero.getName())) {
                this.threadBomberman = new Thread(() -> {
                    try {
                        checkCorrectPut(hero);
                        this.board[hero.getCell().getX()][hero.getCell().getY()].tryLock();
                        while (!Thread.currentThread().isInterrupted()) {
                            Cell newSource;
                            Cell dest;
                            boolean result;
                            do {
                                /*System.out.println("Enter the direction: 2 - down, 8 - up, 6 - right, 4 - left");
                                dest = userDest(hero.getCell(), new Scanner(System.in).nextInt());*/
                                dest = this.autoDest(hero.getCell());
                                result = move(hero.getCell(), dest);
                                if (!result) {
                                    System.out.println("Incorrect direction!");
                                }
                            }
                            while (!result);
                            board[hero.getCell().getX()][hero.getCell().getY()].unlock();
                            newSource = dest;
                            // System.out.println("new Source Cell для " + hero.getName() + " = " + newSource);
                            hero.setCell(newSource);
                            //запуск отображения состояния массива, когда добавлен только Bomberman:
                            //this.getStateOfBoard();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                });
                this.threadBomberman.setName("Thread for Bomberman");
                this.threadBomberman.start();

                Thread checkHasQueuedThreads = new Thread(() -> {
                    while(true) {
                          System.out.println("checking:" + this.board[hero.getCell().getX()][hero.getCell().getY()].hasQueuedThreads());
                        if (this.board[hero.getCell().getX()][hero.getCell().getY()].hasQueuedThreads()) {
                            this.threadBomberman.interrupt();
                            System.out.println(this.threadBomberman.getName() + " is " + this.threadBomberman.isInterrupted());
                        }
                    }
                });
                checkHasQueuedThreads.setDaemon(true);
                checkHasQueuedThreads.start();

            } else {
                threadsForMonsters.add(
                new Thread(() -> {
                    try {
                        checkCorrectPut(hero);
                        this.board[hero.getCell().getX()][hero.getCell().getY()].tryLock();
                        while (true) {
                            Cell newSource;
                            Cell dest;
                            boolean result;
                            do {
                                dest = this.autoDest(hero.getCell());
                                result = move(hero.getCell(), dest);
                            }
                            while (!result);
                            //   System.out.println("3 " + cellIsLock(hero.getCell()) + " " + hero.getCell().toString() + "  current Thread is " + Thread.currentThread().getName());
                            board[hero.getCell().getX()][hero.getCell().getY()].unlock();
                            //   System.out.println("4 " + cellIsLock(hero.getCell()));
                            newSource = dest;
                            // System.out.println("new Source Cell для " + hero.getName() + " = " + newSource);
                            hero.setCell(newSource);
                            //запуск отображения состояния массива:
                            this.getStateOfBoard();
                           // Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }));
            }
        }
        for (Thread thread : threadsForMonsters) {

            numberOfMonsterThread++;
            thread.setName("Thread for Monster " + numberOfMonsterThread);
            thread.setDaemon(true);
            thread.start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Завершение игры. Прерывание потока для бомбермена.
     */
    public void stopGame() {
        this.threadBomberman.interrupt();
    }

    public static void main(String[] args) {
        Hero bomberman = new Bomberman(new Cell(0, 0));
        Hero monster = new Monster(new Cell(0, 0));
        List<Hero> characters = new ArrayList<Hero>();
        characters.add(bomberman);
        characters.add(monster);
        //     characters.add(monster);

        Board board = new Board(5, characters);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board.runGame();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board.stopGame();
    }
}