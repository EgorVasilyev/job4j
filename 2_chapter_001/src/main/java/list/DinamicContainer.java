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
    protected boolean changed = false;


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
            Object[] tempContainer = this.container;
            this.size = this.size + 10;
            this.container = new Object[size];
            System.arraycopy(tempContainer, 0, this.container, 0, this.size - 10);
        }
        this.container[index++] = model;
        changed = true;
    }

    /**
     * Получение элемента по индексу.
     * @param index индекс.
     * @return элемент типа Т.
     */
    public T get(int index) {
        T result = null;
        if (index > 0 && index < this.container.length) {
            result = (T) this.container[index];
        }
        return result;
    }

    /**
     * Итератор.
     * @return Итератор.
     */
    @Override
    public Iterator<T> iterator() {
        //Обновляем переменную change при создании Итератора.
        changed = false;
        return new Iterator<T>() {
            private int iter = 0;
            @Override
            public boolean hasNext() {
                return (iter < index);
            }

            @Override
            public T next() {
                if (changed) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[iter++];
            }
        };
    }
}