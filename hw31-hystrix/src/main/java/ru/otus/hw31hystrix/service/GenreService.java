package ru.otus.hw31hystrix.service;

import ru.otus.hw31hystrix.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();
}
