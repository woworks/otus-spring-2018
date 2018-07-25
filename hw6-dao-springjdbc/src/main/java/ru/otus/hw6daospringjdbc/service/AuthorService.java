package ru.otus.hw6daospringjdbc.service;

import ru.otus.hw6daospringjdbc.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void insert(String authorName);
}
