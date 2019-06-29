package ru.job4j.htmlcssjs.persistent;

import ru.job4j.htmlcssjs.datamodel.JsonUser;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStore implements Store {
    private final ConcurrentHashMap<Integer, JsonUser> users;
    private static Store singletonInstance = new MemoryStore();
    private AtomicInteger id = new AtomicInteger(0);

    private MemoryStore() {
        this.users = new ConcurrentHashMap<Integer, JsonUser>();
    }

    public static Store getSingletonInstance() {
        return singletonInstance;
    }
    @Override
    public void add(JsonUser user) {
        user.setId(id.incrementAndGet());
        this.users.put(user.getId(), user);
    }

    @Override
    public void update(int id, JsonUser user) {
        Optional<JsonUser> userOptional = Optional.ofNullable(this.findById(id));
        userOptional.ifPresent(userValue -> {
            userValue.setName(user.getName());
            userValue.setLastName(user.getLastName());
            userValue.setSex(user.getSex());
            userValue.setDescription(user.getDescription());
        });
    }

    @Override
    public void delete(int id) {
        this.users.remove(id);
    }

    @Override
    public ConcurrentHashMap<Integer, JsonUser> findAll() {
        return this.users;
    }

    @Override
    public JsonUser findById(int id) {
        return this.users.get(id);
    }
}
