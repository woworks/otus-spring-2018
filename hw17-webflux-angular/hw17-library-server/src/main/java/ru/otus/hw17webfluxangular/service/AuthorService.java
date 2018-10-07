package ru.otus.hw17webfluxangular.service;

import reactor.core.publisher.Flux;
import ru.otus.hw17webfluxangular.domain.Author;

public interface AuthorService {

    Flux<Author> getAll();

    void insert(Author author);
}
