package ru.otus.hw10springdatajpa.service;

import org.springframework.stereotype.Service;
import ru.otus.hw10springdatajpa.repository.AuthorsDao;
import ru.otus.hw10springdatajpa.domain.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorsDao authorsDao;

    public AuthorServiceImpl(AuthorsDao authorsDao) {
        this.authorsDao = authorsDao;
    }

    @Override
    public List<Author> getAll() {
        return this.authorsDao.findAll();
    }

    @Override
    public void insert(Author author) {
        this.authorsDao.save(author);
    }
}
