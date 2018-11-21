package ru.otus.hw28docker.service;

import ru.otus.hw28docker.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void insert(Author author);
}
