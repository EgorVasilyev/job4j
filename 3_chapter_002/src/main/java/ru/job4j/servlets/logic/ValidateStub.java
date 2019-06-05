package ru.job4j.servlets.logic;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.datamodel.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 1;
    @Override
    public User add(User user) {
        user.setId(this.ids++);
        this.store.put(user.getId(), user);
        return user;
    }

    @Override
    public void update(int id, User user) {
        Optional<User> userOptional = Optional.ofNullable(this.findById(id));
        userOptional.ifPresent(userValue -> {
            userValue.setName(user.getName());
            userValue.setLogin(user.getLogin());
            userValue.setEmail(user.getEmail());
            userValue.setPassword(user.getPassword());
            userValue.setRole(user.getRole());
        });
    }

    @Override
    public boolean delete(int id) {
        this.store.remove(id);
        return !this.store.containsKey(id);
    }

    @Override
    public ConcurrentHashMap<Integer, User> findAll() {
        return new ConcurrentHashMap<>(store);
    }

    @Override
    public User findById(int id) {
        return this.store.get(id);
    }

    @Override
    public int isCredential(String login, String password) {
        return 0;
    }
}
