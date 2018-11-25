package ru.otus.hw31hystrix.service;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import ru.otus.hw31hystrix.repository.GenresDao;
import ru.otus.hw31hystrix.domain.Genre;

import java.util.Collections;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenresDao genresDao;

    public GenreServiceImpl(GenresDao genresDao) {
        this.genresDao = genresDao;
    }

    @HystrixCommand(fallbackMethod = "getDefaultAll", groupKey = "GenreService", commandKey = "getAll")
    @Override
    public List<Genre> getAll() {
        return Lists.newArrayList(this.genresDao.findAll());
    }

    // returning empty list to give DB time to recover
    public List<Genre> getDefaultAll() {
        return Collections.emptyList();
    }
}
