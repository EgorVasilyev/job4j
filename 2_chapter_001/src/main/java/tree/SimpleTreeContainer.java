package tree;

import java.util.Optional;
/**
 * Interface SimpleTreeContainer - Контейнер дерева. Решение задач уровня Junior. Части 001. Collections. Pro.
 * 1. Создать элементарную структуру дерева[#92230]
 *
 * @author Egor Vasilyev
 * @since 09.01.2019
 * @version 1
 */
public interface SimpleTreeContainer<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Метод add. Добавление элемента child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return
     */
    boolean add(E parent, E child);
    /**
     * Метод findBy. Поиск элемента в дереве по значению.
     * @param value Значение.
     * @return Элемент.
     */
    Optional<Node<E>> findBy(E value);
}