package ru.otus.hw8springormjpa.repository;

import ru.otus.hw8springormjpa.domain.User;

import java.util.Optional;

public interface UsersDao {
    Optional<User> getUserByUsername(String userName);

    void insert(User user);
}
