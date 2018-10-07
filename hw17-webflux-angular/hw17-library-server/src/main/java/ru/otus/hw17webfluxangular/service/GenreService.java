package ru.otus.hw17webfluxangular.service;

import reactor.core.publisher.Flux;
import ru.otus.hw17webfluxangular.domain.Genre;

public interface GenreService {

    Flux<Genre> getAll();
}
