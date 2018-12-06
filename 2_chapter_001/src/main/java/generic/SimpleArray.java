package generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 1
 * @since 06.12.2018
 */

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int index = 0;
    private int iter = 0;

    /**
     * Конструктор.
     * @param size размер массива.
     */
    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * Добавление элемента.
     * @param model новый элемент.
     */
    public void add(T model) {
        if (index < this.array.length) {
            this.array[index++] = model;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Запись элемента по индексу.
     * @param index индекс.
     * @param model элемент.
     */
    public void set(int index, T model) {
        this.array[index] = model;
    }

    /**
     * Удаление элемента из массива.
     * @param index индекс.
     */
    public void delete(int index) {
        int nextIndex = index + 1;
        System.arraycopy(array, nextIndex, array, index, array.length - (index + 1));
    }

    /**
     * Получение элемента по индексу.
     * @param index индекс.
     * @return элемент типа Т.
     */
    public T get(int index) {
        return (T) this.array[index];
    }

    /**
     * Итератор.
     * @return Итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return (iter < index);
            }

            @Override
            public T next() {
                if (iter < index) {
                    return (T) array[iter++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
