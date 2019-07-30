package search;

import org.junit.Test;
import ru.job4j.search.PriorityQueue;
import ru.job4j.search.Task;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("five", 5));
        queue.put(new Task("two", 2));
        queue.put(new Task("nine", 9));
        queue.put(new Task("one", 1));
        queue.put(new Task("three", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("one"));
    }
}