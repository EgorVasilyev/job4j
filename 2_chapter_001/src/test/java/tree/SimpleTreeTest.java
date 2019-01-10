package tree;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * Class SimpleHashMapTest - Отображения. Автотесты для решения задач уровня Junior. Части 001. Collections. Pro.
 * 1. Создать элементарную структуру дерева[#92230]
 *
 * @author Egor Vasilyev
 * @since 09.01.2019
 * @version 1
 */
public class SimpleTreeTest {
    /**
     * Тест вставки в дерево и поиск существующего элемента..
     */
    @Test
    public void when6ElFindLastThen6() {
        SimpleTree<Integer> tree = new SimpleTree(new Node(1));
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(1, 4));
        assertTrue(tree.add(4, 5));
        assertTrue(tree.add(5, 6));
        assertThat(tree.findBy(6).isPresent(), is(true));
    }
    /**
     * Тест вставки в дерево и поиск несуществующего элемента.
     */
    @Test
    public void when6ElFindNotExistsThenOptionEmpty() {
        SimpleTree<Integer> tree = new SimpleTree(new Node(1));
        tree.add(1, 2);
        assertThat(tree.findBy(6).isPresent(), is(false));
    }
    /**
     * Тест последовательного прохода по дереву.
     */
    @Test(expected = NoSuchElementException.class)
    public void testIteratorSequentiallyFetch() {
        SimpleTree<Integer> tree = new SimpleTree<>(new Node(1));
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(1, 4));
        assertTrue(tree.add(4, 5));
        assertTrue(tree.add(5, 6));
        Iterator<Integer> it = tree.iterator();
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(2));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(3));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(4));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(5));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(6));
        MatcherAssert.assertThat(it.hasNext(), is(false));
        it.next();
    }
    /**
     * Тест последовательного прохода по дереву с модификацией.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void testIteratorConcurrentModificationException() {
        SimpleTree<Integer> tree = new SimpleTree<>(new Node(1));
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(1, 4));
        assertTrue(tree.add(4, 5));
        assertTrue(tree.add(5, 6));
        Iterator<Integer> it = tree.iterator();
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(2));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(3));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        assertTrue(tree.add(5, 7));
        it.next();
    }
    /**
     * Тест последовательного прохода по дереву двух итераторов.
     */
    @Test
    public void testTwoIterators() {
        SimpleTree<Integer> tree = new SimpleTree<>(new Node(1));
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(1, 4));
        assertTrue(tree.add(4, 5));
        assertTrue(tree.add(5, 6));
        Iterator<Integer> it = tree.iterator();
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(2));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(3));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        Iterator<Integer> itTwo = tree.iterator();
        MatcherAssert.assertThat(itTwo.hasNext(), is(true));
        MatcherAssert.assertThat(itTwo.next(), is(1));
        MatcherAssert.assertThat(itTwo.hasNext(), is(true));
        MatcherAssert.assertThat(itTwo.next(), is(2));
        MatcherAssert.assertThat(itTwo.hasNext(), is(true));
        MatcherAssert.assertThat(itTwo.next(), is(3));
        MatcherAssert.assertThat(itTwo.hasNext(), is(true));
    }
}