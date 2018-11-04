package ru.otus.hw21springacl.service;

import ru.otus.hw21springacl.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void insert(Author author);
}
