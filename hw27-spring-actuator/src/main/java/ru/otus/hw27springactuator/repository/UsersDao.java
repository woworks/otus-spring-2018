package ru.otus.hw27springactuator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw27springactuator.domain.User;

import java.util.Optional;

@Repository
public interface UsersDao extends CrudRepository<User, Long> {

    Optional<User> getUserByUsername(String userName);
}
