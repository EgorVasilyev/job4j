package sql;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {
    private TrackerSQL sql;
    @Before
    public void checkConnection() {
        sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }
    @After
    public void closeConnection() {
        sql.close();
    }
    @Test
    public void whenAddOneAndFindAllThenOneItem() {
        sql.add(new Item("firstName", "firstDesc"));
        String expectedName = "firstName";
        String expectedDesc = "firstDesc";
        assertThat(sql.findAll().size(), is(1));
        assertThat(sql.findAll().get(0).getName(), is(expectedName));
        assertThat(sql.findAll().get(0).getDescription(), is(expectedDesc));
        sql.deleteItemsTable();
    }
    @Test
    public void whenAddOneAndReplaceThenNewName() {
        Item firstItem = new Item("firstName", "firstDesc");
        sql.add(firstItem);
        sql.replace(firstItem.getId(), new Item("replacedName", "replacedDesc"));
        String expectedName = "replacedName";
        String expectedDesc = "replacedDesc";
        assertThat(sql.findAll().size(), is(1));
        assertThat(sql.findAll().get(0).getName(), is(expectedName));
        assertThat(sql.findAll().get(0).getDescription(), is(expectedDesc));
        sql.deleteItemsTable();
    }
    @Test
    public void whenAddOneAndDeleteThenThereIsNotAnItem() {
        Item firstItem = new Item("firstName", "firstDesc");
        sql.add(firstItem);
        sql.delete(firstItem.getId());
        assertThat(sql.findAll().size(), is(0));
        sql.deleteItemsTable();
    }
    @Test
    public void whenAddTwoAndFindAllThenTwoItem() {
        sql.add(new Item("firstName", "firstDesc"));
        sql.add(new Item("secondName", "secondDesc"));
        String expectedName = "firstName";
        String expectedDesc = "secondDesc";
        assertThat(sql.findAll().size(), is(2));
        assertThat(sql.findAll().get(0).getName(), is(expectedName));
        assertThat(sql.findAll().get(1).getDescription(), is(expectedDesc));
        sql.deleteItemsTable();
    }
    @Test
    public void whenAddOneAndFindByIdThenOneItem() {
        Item firstItem = new Item("firstName", "firstDesc");
        sql.add(firstItem);
        Item resultItem = sql.findById(firstItem.getId());
        String expectedName = "firstName";
        String expectedDesc = "firstDesc";
        assertThat(resultItem.getName(), is(expectedName));
        assertThat(resultItem.getDescription(), is(expectedDesc));
        sql.deleteItemsTable();
    }
    @Test
    public void whenAddTwoAndFindByNameThentwoItem() {
        Item firstItem = new Item("Name", "firstDesc");
        Item secondItem = new Item("Name", "secondDesc");
        sql.add(firstItem);
        sql.add(secondItem);
        String expectedFirstDesc = "firstDesc";
        String expectedSecondDesc = "secondDesc";
        assertThat(sql.findByName("Name").get(0).getDescription(), is(expectedFirstDesc));
        assertThat(sql.findByName("Name").get(1).getDescription(), is(expectedSecondDesc));
        sql.deleteItemsTable();
    }
}
