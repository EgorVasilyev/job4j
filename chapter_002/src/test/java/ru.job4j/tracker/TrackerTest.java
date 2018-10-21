package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.models.Item;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }
    @Test
    public void whenFindByNameTest1TwiceThenTrackerReturnArrayHavingTwoItemsWithNameTest1() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription");
        Item item2 = new Item("test1", "testDescription2");
        Item item3 = new Item("test3", "testDescription3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        String key = "test1";
        assertThat(tracker.findByName(key)[0].getName(), is(key));
        assertThat(tracker.findByName(key)[1].getName(), is(key));
        String key2 = "test3";
        assertThat(tracker.findByName(key2)[0].getName(), is(key2));
    }
    @Test
    public void whenFindByIdThenItem2() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription");
        Item item2 = new Item("test2", "testDescription2");
        Item item3 = new Item("test3", "testDescription3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findById(item2.getId()), is(item2));
    }
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription");
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2");
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
        assertThat(next, is(tracker.findAll()[0]));
    }
    @Test
    public void whenDeleteFindByIdGiveNull() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription");
        tracker.add(item1);
        tracker.delete(item1.getId());
        Item result = tracker.findById(item1.getId());
        Item nothing = null;
        assertThat(result, is(nothing));
    }
    @Test
    public void whenFindAllThenThisItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription");
        tracker.add(item1);
        Item item2 = new Item("test2", "testDescription");
        tracker.add(item2);
        assertThat(tracker.findAll()[0], is(item1));
        assertThat(tracker.findAll()[1], is(item2));
    }
}