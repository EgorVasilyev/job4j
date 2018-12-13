package list;
/**
 * @author Egor
 * @version 1
 * @since 12.12.2018
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс SimpleArrayList.
 */
public class DinamicContainerLL<E> implements Iterable<E> {

    private int size;
    private Node<E> first;
    private int changed = 0;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        changed++;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод возвращает первый элемент, удаляя его из коллекции.
     * return удаленный элемент типа E.
     */
    public E delete() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        this.first = this.first.next;
        this.size--;
        return result.date;
    }


    @Override
    public Iterator<E> iterator() {


        return new Iterator<E>() {
            Node iterLink = DinamicContainerLL.this.first;
            private int iter = 0;
            int countMod = changed;
            @Override
            public boolean hasNext() {
                if (countMod != changed) {
                    throw new ConcurrentModificationException();
                }
                return (iter < size);
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (iter != 0) {
                    iterLink = iterLink.next;
                }
                iter++;
                return (E) iterLink.date;
            }

        };
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
