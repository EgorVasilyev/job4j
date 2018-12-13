package list;

        import org.junit.Test;

        import java.util.NoSuchElementException;

        import static org.hamcrest.Matchers.is;
        import static org.junit.Assert.assertThat;

public class SimpleQueueTest {

    private SimpleQueue<Integer> queue;

    @Test
    public void whenPushThreeElementsThenCheckFirstInputFirstOutput() {
        queue = new SimpleQueue<>();
        queue.push(1);
        assertThat(queue.poll(), is(1));
        queue.push(2);
        assertThat(queue.poll(), is(2));
        queue.push(3);
        assertThat(queue.poll(), is(3));
        queue.push(11);
        queue.push(22);
        queue.push(33);
        assertThat(queue.poll(), is(11));
        assertThat(queue.poll(), is(22));
        assertThat(queue.poll(), is(33));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenThereAreNotElementsAndCallsPollThenNoSuchElemException() {
        queue = new SimpleQueue<>();
        queue.poll();
    }
}