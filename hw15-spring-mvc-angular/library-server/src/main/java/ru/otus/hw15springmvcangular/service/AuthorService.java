package ru.otus.hw15springmvcangular.service;

import ru.otus.hw15springmvcangular.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void insert(Author author);
}
