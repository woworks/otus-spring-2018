package ru.otus.hw31hystrix.service;

import ru.otus.hw31hystrix.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void insert(Author author);
}
