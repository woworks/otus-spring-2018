package ru.otus.hw6daospringjdbc.service;

import org.springframework.stereotype.Service;
import ru.otus.hw6daospringjdbc.dao.AuthorsDao;
import ru.otus.hw6daospringjdbc.domain.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorsDao authorsDao;

    public AuthorServiceImpl(AuthorsDao authorsDao) {
        this.authorsDao = authorsDao;
    }

    @Override
    public List<Author> getAll() {
        return this.authorsDao.getAll();
    }

    @Override
    public void insert(String authorName) {
        this.authorsDao.insert(authorName);
    }
}
