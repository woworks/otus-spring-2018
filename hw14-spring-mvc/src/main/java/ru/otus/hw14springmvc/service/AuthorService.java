package ru.otus.hw14springmvc.service;

import ru.otus.hw14springmvc.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void insert(Author author);
}
