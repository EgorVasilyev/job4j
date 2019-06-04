package ru.job4j.servlets.persistent;

import ru.job4j.servlets.datamodel.User;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Persistent Layout
 *
 * Создать интерфейс Store c методами add, update, delete, findAll, findById
 *
 * Сделать реализацию этого интерфейса MemoryStore. Сделать из него синлетон.
 *
 * Класс MemoryStore - должен быть потокобезопастный. вы можете использовать внутри потокбезопастные коллекции.
 *
 *
 * В web.xml указать для UserServiler режим load-on-startup
 *
 * http://www.xyzws.com/servletfaq/what-is-%3Cloadonstartup%3E-in-webxml-file/24
 */

public class MemoryStore implements Store {
    private final ConcurrentHashMap<Integer, User> users;
    private AtomicInteger id = new AtomicInteger(0);

    // Static member holds only one instance of the
    // MemoryStore class
    private static Store singletonInstance =
            new MemoryStore();

    // MemoryStore prevents any other class from instantiating
    private MemoryStore() {
        this.users = new ConcurrentHashMap<Integer, User>();
    }

    // Providing Global point of access
    public static Store getSingletonInstance() {
        return singletonInstance;
    }
    @Override
    public void add(User user) {
        user.setId(id.incrementAndGet());
        this.users.put(user.getId(), user);
    }

    @Override
    public void update(int id, User user) {
        Optional<User> userOptional = Optional.ofNullable(this.findById(id));
        userOptional.ifPresent(userValue -> {
            userValue.setName(user.getName());
            userValue.setLogin(user.getLogin());
            userValue.setEmail(user.getEmail());
        });
    }

    @Override
    public void delete(int id) {
        this.users.remove(id);
    }

    @Override
    public ConcurrentHashMap<Integer, User> findAll() {
        return this.users;
    }

    @Override
    public User findById(int id) {
        return this.users.get(id);
    }
}
