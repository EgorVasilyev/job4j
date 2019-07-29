package job4j.storage.store;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.storage.ImportUser;
import ru.job4j.storage.entity.User;

import static org.junit.Assert.assertEquals;

public class ImportUserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setName("name");
        user.setAge(20);
    }

    @Test
    public void whenAddUserToStoreThenThereIsNewUserInStore() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("storageContext.xml");
        ImportUser importUser = context.getBean("importUser", ImportUser.class);
        importUser.add(user);
        assertEquals(importUser.getUsers().get(0), user);
    }
}