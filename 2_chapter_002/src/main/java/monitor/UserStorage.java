package monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
/**
 * Класс - хранилище для User.
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private HashSet<User> users = new HashSet<User>();
    /**
     * Метод добавляет объект user в хранилище.
     */
    public synchronized boolean add(User user) {
        boolean resultAdd = false;
        if (user != null) {
            resultAdd = this.users.add(user);
        }
        return resultAdd;
    }
    /**
     * Метод обновляет объект user.
     */
    public synchronized boolean update(User user) {
        boolean resultUp = false;
        if (this.users.remove(user)) {
            this.users.add(user);
            resultUp = true;
        }
        return resultUp;
    }
    /**
     * Метод удаляет объект user из коллекции.
     */
    public synchronized boolean delete(User user) {
        return this.users.remove(user);
    }
    /**
     * Метод переводит сумму от одного пользователья к другому.
     * @param fromId - id первого пользователя
     * @param toId - id второго пользователя
     * @param amount - размер перевода
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean resultTransf = false;
        User userFrom = null;
        User userTo = null;
        for (User user : users) {
            if (user.getId() == fromId) {
                userFrom = user;
            }
            if (user.getId() == toId) {
                userTo = user;
            }
        }
        if (userFrom != null
                && userTo != null
                && this.users.contains(userFrom)
                && this.users.contains(userTo)
                && amount > 0
                && userFrom.getAmount() >= amount) {
            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() + amount);
            update(userFrom);
            update(userTo);
            resultTransf = true;
        }
        return resultTransf;
    }
}
