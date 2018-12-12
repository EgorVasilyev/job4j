package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 1
 * @since 11.12.2018
 */

public class DinamicContainer<T> implements Iterable<T> {
    private Object[] container;
    private int index = 0;
    private int size = 3;
    private int changed = 0;


    /**
     * Конструктор.
     */
    public DinamicContainer() {
        this.container = new Object[size];
    }

    /**
     * Добавление элемента.
     * @param model новый элемент.
     */
    public void add(T model) {
        if (index == this.container.length) {
            this.increaseSize();
        }
        this.container[index++] = model;
        changed++;
    }

    /**
     * Увеличение размера контейнера.
     */
    private void increaseSize() {
        Object[] tempContainer = this.container;
        this.size = this.size + 10;
        this.container = new Object[size];
        System.arraycopy(tempContainer, 0, this.container, 0, this.size - 10);
    }

    /**
     * Получение элемента по индексу.
     * @param index индекс.
     * @return элемент типа Т.
     */
    public T get(int index) {
        if (this.index == 0 || index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        T result = (T) this.container[index];
        return result;
    }

    /**
     * Итератор.
     * @return Итератор.
     */
    @Override
    public Iterator<T> iterator() {
        int countMod = changed;
        return new Iterator<T>() {
            private int iter = 0;
            @Override
            public boolean hasNext() {
                if (countMod != changed) {
                    throw new ConcurrentModificationException();
                }
                return (iter < index);
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[iter++];
            }
        };
    }
}