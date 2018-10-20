package ru.otus.hw19springsecurity.service;

import ru.otus.hw19springsecurity.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void insert(Author author);
}
