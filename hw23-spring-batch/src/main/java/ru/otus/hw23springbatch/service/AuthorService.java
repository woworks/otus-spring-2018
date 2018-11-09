package ru.otus.hw23springbatch.service;

import ru.otus.hw23springbatch.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void insert(Author author);
}
