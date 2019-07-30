package search;
import org.junit.Test;
import ru.job4j.search.Person;
import ru.job4j.search.PhoneDictionary;

import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindBySurname() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Egor", "Vasilyev", "9192225", "Moscow")
        );
        List<Person> persons = phones.find("ily");
        assertThat(persons.iterator().next().getName(), is("Egor"));
    }
}