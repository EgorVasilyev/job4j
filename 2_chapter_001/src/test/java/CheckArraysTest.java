import generic.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckArraysTest {
    Integer[] arrayA;
    User[] usersA;
    Character[] charA;
    @Before
    public void createArrayA() {
        arrayA = new Integer[]{1, 2, 3, 4, 5};
        usersA = new User[]{new User("FirstUser"), new User("SecondUser")};
        charA = new Character[]{'m', 'a', 'm', 'a'};
    }
    @Test
    public void whenSameArraysThenTrue() {
        Integer[] arrayB = {1, 2, 3, 4, 5};
        User[] usersB = new User[]{new User("FirstUser"), new User("SecondUser")};

        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };

        assertTrue(CheckArrays.equals(arrayA, arrayB));
        assertTrue(CheckArrays.equals(usersA, usersB, comparator));
    }
    @Test
    public void whenDifferentSizeOfArraysThenFalse() {
        Integer[] arrayB = {1, 2, 3, 4};
        assertFalse(CheckArrays.equals(arrayA, arrayB));
    }
    @Test
    public void whenDifferentLocationOfElementsThenFalse() {
        Integer[] arrayB = {5, 2, 3, 4, 1};
        assertFalse(CheckArrays.equals(arrayA, arrayB));
    }
    @Test
    public void whenSameSomeElementsThenFalse() {
        Integer[] arrayB = {1, 1, 1, 1, 1};
        assertFalse(CheckArrays.equals(arrayA, arrayB));
    }
    @Test
    public void whenDifferentArraysThenFalse() {
        Integer[] arrayB = {213, 214, 1, 5, 5, 123, 6656};
        User[] usersB = new User[]{new User("ThirdUser"), new User("SecondUser")};
        Character[] charB = new Character[]{'m', 'a', 'a', 'a'};

        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };
        assertFalse(CheckArrays.equals(arrayA, arrayB));
        assertFalse(CheckArrays.equals(usersA, usersB, comparator));
        assertFalse(CheckArrays.equals(charA, charB));
    }
}
