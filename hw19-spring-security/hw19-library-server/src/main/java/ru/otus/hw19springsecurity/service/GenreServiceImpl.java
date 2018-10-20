package ru.otus.hw19springsecurity.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import ru.otus.hw19springsecurity.repository.GenresDao;
import ru.otus.hw19springsecurity.domain.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenresDao genresDao;

    public GenreServiceImpl(GenresDao genresDao) {
        this.genresDao = genresDao;
    }

    @Override
    public List<Genre> getAll() {
        return Lists.newArrayList(this.genresDao.findAll());
    }
}
