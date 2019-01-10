package tree;

import java.util.*;
/**
 * Class SimpleTree - Дерево. Решение задач уровня Junior. Части 001. Collections. Pro.
 * 1. Создать элементарную структуру дерева[#92230]
 *
 * @author Egor Vasilyev
 * @since 09.01.2019
 * @version 1
 */
public class SimpleTree<E extends Comparable<E>> implements SimpleTreeContainer<E> {
    private Node<E> root;
    private int size = 0;
    private int modCount = 0;
    /**
     * Метод SimpleTree. Конструктор.
     * @param root Корневой элемент.
     */
    public SimpleTree(Node<E> root) {
        this.root = root;
        this.size++;
    }
    /**
     * Метод add. Добавление элемента child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        boolean res = false;
        Optional<Node<E>> op;
        op = findBy(parent);
        if (op.isPresent()) {
            Node<E> node = new Node<>(child);
            if (op.get().add(node)) {
                res = true;
                this.size++;
                modCount++;
            }
        }
        return res;
    }
    /**
     * Метод findBy. Поиск элемента в дереве по значению.
     * @param value Значение.
     * @return Элемент.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Метод getRoot. Получение корневого элемента.
     * @return Элемент.
     */
    public Node<E> getRoot() {
        return this.root;
    }
    @Override
    public Iterator<E> iterator() {
        Queue<Node<E>> iter = new LinkedList<>();
        iter.add(root);
        return new Iterator<E>() {

            private int nextIndex = 0;
            int expectedModCount = modCount;
            /**
             * Method hasNext. Проверка наличия следующего элемента.
             * @return Наличие элемента.
             */
            @Override
            public boolean hasNext() {
                if (modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return this.nextIndex < size;
            }
            /**
             * Method next. Получение следующего элемента массива.
             * @return Элемент.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.nextIndex++;
                Node<E> result = iter.poll();
                for (Node<E> child : result.leaves()) {
                    iter.offer(child);
                }
                return result.getValue();
            }
        };
    }
}
