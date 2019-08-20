package ru.job4j.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.entity.user.User;
import ru.job4j.repository.user.UserDataRepository;

import java.util.List;
@Service
public class UserService {
    private final UserDataRepository userDataRepository;
    @Autowired
    public UserService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public int save(User user) {
        int id = 0;
        if (user != null) {
            userDataRepository.save(user);
            id = user.getId();
        }
        return id;
    }
    public void update(User user) {
        if (user != null) {
            userDataRepository.save(user);
        }
    }
    public void delete(User user) {
        if (user != null) {
            userDataRepository.delete(user);
        }
    }
    public List<User> getUsers() {
        return userDataRepository.findAllByOrderByIdAsc();
    }
    public User isCredential(String login, String password) {
        return this.getUsers().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean contains(String login) {
        return this.getUsers().stream().anyMatch(user -> user.getLogin().equals(login));
    }


    public User getUserById(int id) {
        return userDataRepository.findUserById(id);
    }

    public User getUserByLogin(String login) {
        return userDataRepository.findUserByLogin(login);
    }
}
