package list;
/**
 * @author Egor
 * @version 1
 * @since 12.12.2018
 */
import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private int size;
    private Node<T> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void push(T date) {
        Node<T> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод возвращает первое значение из связанного списка и удаляет его.
     */
    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node<T> result = this.first;
        this.first = this.first.next;
        this.size--;
        return result.date;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<T> {

        T date;
        Node<T> next;

        Node(T date) {
            this.date = date;
        }
    }
}
