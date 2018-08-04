package ru.otus.hw10springdatajpa.service;

import ru.otus.hw10springdatajpa.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void insert(Author author);
}
