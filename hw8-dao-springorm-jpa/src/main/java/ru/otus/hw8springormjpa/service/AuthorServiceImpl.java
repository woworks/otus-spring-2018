package ru.otus.hw8springormjpa.service;

import org.springframework.stereotype.Service;
import ru.otus.hw8springormjpa.repository.AuthorsDao;
import ru.otus.hw8springormjpa.domain.Author;

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
    public void insert(Author author) {
        this.authorsDao.insert(author);
    }
}
