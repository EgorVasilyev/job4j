package control;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {
    @Test
    public void testStatistic() {
        List<Analize.User> prev = new ArrayList<>();
        //creat previous list
        Analize.User user1 = new Analize.User(1, "user1");
        Analize.User user2 = new Analize.User(2, "user2");
        Analize.User user3 = new Analize.User(3, "user3");
        prev.add(user1);
        prev.add(user2);
        prev.add(user3);
        //creat current list
        List<Analize.User> curr = new ArrayList<>();

        Analize.User user1changed = new Analize.User(1, "userChanged");
        Analize.User user4 = new Analize.User(4, "user4");
        Analize.User user5 = new Analize.User(5, "user5");
        curr.add(user1changed);
        curr.add(user2);
        curr.add(user4);
        curr.add(user5);

        Analize analize = new Analize();
        assertThat(analize.diff(prev, curr).toString(), is("added=2, changed=1, deleted=1"));

    }

}
