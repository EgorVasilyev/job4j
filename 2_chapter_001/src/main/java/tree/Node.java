package tree;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Class Node - Элемент дерева. Решение задач уровня Junior. Части 001. Collections. Pro.
 * 1. Создать элементарную структуру дерева[#92230]
 *
 * @author Egor Vasilyev
 * @since 09.01.2019
 * @version 1
 */
public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;
    /**
     * Метод Node. Конструктор.
     * @param value Значение.
     */
    public Node(final E value) {
        this.value = value;
    }
    /**
     * Метод getValue. Получение значения элемента.
     * @return Значение.
     */
    public E getValue() {
        return value;
    }
    /**
     * Метод add. Добавление элемента.
     * @param child child.
     * @return
     */
    public boolean add(Node<E> child) {
        boolean res = false;
        if (!this.children.contains(child)) {
            this.children.add(child);
            res = true;
        }
        return res;
    }
    /**
     * Метод leaves. Получение списка children.\
     * @return Список children.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }
    /**
     * Метод eqValue. Определение равенства по значению.
     * @return
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    @Override
    public String toString() {
        return "Node{" + "children=" + children.size() + ", value=" + value + '}';
    }
}
