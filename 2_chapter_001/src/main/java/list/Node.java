package list;

/**
 * Класс предназначен для хранения данных.
 */
public class Node<E> {
    E date;
    Node<E> next;

    Node(E date) {
        this.date = date;
    }
}
