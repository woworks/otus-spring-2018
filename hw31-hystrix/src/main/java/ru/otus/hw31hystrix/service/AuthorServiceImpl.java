package ru.otus.hw31hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import ru.otus.hw31hystrix.repository.AuthorsDao;
import ru.otus.hw31hystrix.domain.Author;

import java.util.Collections;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorsDao authorsDao;

    public AuthorServiceImpl(AuthorsDao authorsDao) {
        this.authorsDao = authorsDao;
    }


    @HystrixCommand(fallbackMethod = "getDefaultAll", groupKey = "AuthorService", commandKey = "getAll")
    @Override
    public List<Author> getAll() {
        return this.authorsDao.findAll();
    }

    @Override
    public void insert(Author author) {
        this.authorsDao.save(author);
    }

    // returning empty list to give DB time to recover
    public List<Author> getDefaultAll() {
        return Collections.emptyList();
    }
}
