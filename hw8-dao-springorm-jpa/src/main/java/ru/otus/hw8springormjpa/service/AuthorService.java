package ru.otus.hw8springormjpa.service;

import ru.otus.hw8springormjpa.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void insert(Author author);
}
