package tree;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TreeTest {
    /**
     * Тест дерева на бинароность.
     */
    @Test
    public void when3childThenFalse() {
        Tree<Integer> tree = new Tree(new Node(1));
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(1, 4));
        assertTrue(tree.add(4, 5));
        assertTrue(tree.add(5, 6));
        assertThat(tree.isBinary(), is(false));
    }
    @Test
    public void when2childThenTrue() {
        Tree<Integer> tree = new Tree(new Node(1));
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(2, 4));
        assertTrue(tree.add(2, 5));
        assertTrue(tree.add(5, 6));
        assertThat(tree.isBinary(), is(true));
    }
}