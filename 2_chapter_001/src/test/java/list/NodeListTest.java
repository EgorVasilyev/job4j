package list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NodeListTest {
    @Test
    public void whenListIsCycledThenGetTrue() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);

        first.next = two;
        two.next = third;
        third.next = first;

        NodeList<Integer> list = new NodeList<Integer>(first);
        assertThat(list.hasCycle(), is(true));
    }
    @Test
    public void whenListIsNotCycledThenGetFalse() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;


        NodeList<Integer> list = new NodeList<Integer>(first);
        assertThat(list.hasCycle(), is(false));
    }
}
