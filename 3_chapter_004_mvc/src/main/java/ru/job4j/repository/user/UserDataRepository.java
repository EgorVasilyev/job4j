package ru.job4j.repository.user;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.entity.user.User;

import java.util.List;

public interface UserDataRepository extends CrudRepository<User, Integer> {
    List<User> findAllByOrderByIdAsc();
    User findUserById(int id);
    User findUserByLogin(String login);
}
