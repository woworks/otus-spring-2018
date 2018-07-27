package ru.otus.hw6daospringjdbc.service;

import org.springframework.stereotype.Service;
import ru.otus.hw6daospringjdbc.dao.GenresDao;
import ru.otus.hw6daospringjdbc.domain.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenresDao genresDao;

    public GenreServiceImpl(GenresDao genresDao) {
        this.genresDao = genresDao;
    }

    @Override
    public List<Genre> getAll() {
        return this.genresDao.getAll();
    }
}
