package ru.otus.hw27springactuator.service;

import ru.otus.hw27springactuator.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void insert(Author author);
}
