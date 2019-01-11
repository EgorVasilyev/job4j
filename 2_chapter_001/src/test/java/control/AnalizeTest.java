package control;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {
    @Test
    public void testStatistic() {
        List<Analize.User> list = new ArrayList<>();
        //add three Users
        Analize.User user1 = new Analize.User(1, "user1");
        Analize.User user2 = new Analize.User(2, "user2");
        Analize.User user3 = new Analize.User(3, "user3");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        //creat previous list
        List<Analize.User> prev = new ArrayList<>(list);

        //change one User, remove one User and add two new Users
        user1.setName("changedUser");
        list.remove(user2);
        Analize.User user4 = new Analize.User(4, "user4");
        Analize.User user5 = new Analize.User(5, "user5");
        list.add(user4);
        list.add(user5);
        //creat current list
        List<Analize.User> cur = new ArrayList<>(list);

        //1to2 - compare first condition to second condition
        Analize analize1to2 = new Analize();
        assertThat(analize1to2.diff(prev, cur).toString(), is("added=2, changed=1, deleted=1"));

        //refresh Status of Names
        for (Analize.User user : list) {
            user.refreshStatusName();
        }

        //creat new previous list
        List<Analize.User> prev2 = cur;
        //change two Users, remove two Users and add three new Users
        Analize.User user6 = new Analize.User(6, "user6");
        Analize.User user7 = new Analize.User(7, "user7");
        Analize.User user8 = new Analize.User(8, "user8");
        list.add(user6);
        list.add(user7);
        list.add(user8);
        list.remove(user1);
        list.remove(user4);
        user3.setName("changedUser");
        user5.setName("changedUser");
        //creat new current list
        List<Analize.User> cur2 = new ArrayList<>(list);

        //2to3 - compare second condition to third condition
        Analize analize2to3 = new Analize();
        assertThat(analize2to3.diff(prev2, cur2).toString(), is("added=3, changed=2, deleted=2"));

        //1to3 - compare first condition to third condition
        Analize analize1to3 = new Analize();
        assertThat(analize1to3.diff(prev, cur2).toString(), is("added=4, changed=1, deleted=2"));
    }

}
