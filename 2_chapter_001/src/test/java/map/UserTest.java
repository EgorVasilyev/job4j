package map;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * checking the rules for override the method equals
 */
public class UserTest {
    /**
     * Существуют следующие правила для переопределения этих методов:
     *
     * 1) Рефлексивность: Объект должен равняться себе самому. Для любой ссылки на значение а
     * выражение а.equals(а) должно возвращать true.
     *
     * 2) Симметричность: если a.equals(b) возвращает true, то b.equals(a) должен тоже вернуть true.
     *
     * 3) Транзитивность: если a.equals(b) возвращает true и b.equals(с) тоже возвращает true,
     * то а.equals(с) тоже должен возвращать true.
     *
     * 4) Согласованность (Непротиворечивость): повторный вызов метода equals() должен возвращать одно и тоже
     * значение до тех пор, пока какое-либо значение свойств объекта не будет изменено. То есть, если два
     * объекта равны (неравны) в Java, то они будут равны (неравны) пока их свойства остаются неизменными.
     *
     * 5) Сравнение null: объект должны быть проверен на null. Если объект равен null, то метод должен вернуть
     * false, а не NullPointerException. Например, a.equals(null) должен вернуть false.
     *
     */
    @Test
    /**
     * Test 1.
     * Рефлексивность.
     */
    public void whenEqualsSameObjectShouldTrue() {
        Calendar birthday = new GregorianCalendar(1993, 11, 5);
        User first = new User("Egor", 3, birthday);
        User second = first;
        assertThat(first.equals(second), is(true));
        assertThat(first.equals(first), is(true));

    }
    /**
     * Test 2.
     * Симметричность.
     */
    @Test
    public void whenEqualsMirrorShouldTrue() {

        // тест падает, т.к. метод equals не переопределен и сравнивает ссылки, а не поля объектов

        Calendar birthday = new GregorianCalendar(1993, 11, 5);
        User first = new User("Egor", 3, birthday);
        User second = new User("Egor", 3, birthday);

        assertThat(first.equals(second), is(true));
        assertThat(second.equals(first), is(true));
    }
    /**
     * Test 3.
     * Транзитивность.
     */
    @Test
    public void whenEqualsTransitivityShouldTrue() {

        // тест падает, т.к. метод equals не переопределен и сравнивает ссылки, а не поля объектов

        Calendar birthday = new GregorianCalendar(1993, 11, 5);
        User first = new User("Egor", 3, birthday);
        User second = new User("Egor", 3, birthday);
        User third = new User("Egor", 3, birthday);

        assertThat(first.equals(second), is(true));
        assertThat(second.equals(third), is(true));
        assertThat(first.equals(third), is(true));
    }
    /**
     * Test 4.
     * Согласованность.
     */
    @Test
    public void whenEqualsCoherenceShouldTrue() {

        // тест падает, т.к. метод equals не переопределен и сравнивает ссылки, а не поля объектов

        Calendar birthday = new GregorianCalendar(1993, 11, 5);
        Calendar birthday1 = new GregorianCalendar(1995, 8, 30);
        User first = new User("Egor", 3, birthday);
        User second = new User("Egor", 3, birthday);

        assertThat(first.equals(second), is(true));
        first.setBirthday(birthday1);

        assertThat(first.equals(second), is(false));
    }
    /**
     * Test 4.
     * Сравнение null.
     */
    @Test
    public void whenEqualsNullShouldFalse() {
        Calendar birthday = new GregorianCalendar(1993, 11, 5);
        User first = new User("Egor", 3, birthday);
        User second = null;

        assertThat(first.equals(second), is(false));
    }
    @Test
    public void printMap() {
        Calendar birthday = new GregorianCalendar(1993, 11, 5);
        User first = new User("Egor", 3, birthday);
        User second = new User("Egor", 3, birthday);
        Map<User, String> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");
        System.out.println(map);
        /**
         * 2. Не перекрывать equals hashCode[#92221]
         * результат {map.User@2957fcb0=first, map.User@1376c05c=second}
         *При добавлении значения в HashMap ключ нового элемента сравнивается с ключами имеющихся
         *при этом испльзуются значения методов hashCode() и equals().
         *В этом случае у объектов-ключей first и second будут разные хэши, а также результат вызова
         *метода equals() будет false,т. к. он сравнивает ссылки, а не содержимое объектов, т. е.
         *адреса объектов во внутренней памяти.
         *При выполнении метода put() в HashMap, объекты-ключи first и second являются двумя разными ключами,
         *поэтому произойдет запись двух пар ключ-значение в HashMap.
         *
         * 3. Переопределить только hashCode[#92219]
         * результат {map.User@57992887=first, map.User@57992887=second}
         * При добавлении значения в HashMap ключ нового элемента сравнивается с ключами имеющихся
         * при этом испльзуются значения методов hashCode() и equals().
         * В этом случае у объектов-ключей first и second будут одинаковые хэши, но результат вызова
         * метода equals() будет false,т. к. он сравнивает ссылки, а не содержимое объектов, т. е.
         * адреса объектов во внутренней памяти.
         * Поэтому при выполнении метода put() в HashMap, объекты-ключи first и second являются двумя разными ключами,
         *поэтому произойдет запись двух пар ключ-значение в HashMap.
         */
    }
}