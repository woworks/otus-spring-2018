package ru.otus.hw17webfluxangular.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw17webfluxangular.repository.GenresDao;
import ru.otus.hw17webfluxangular.domain.Genre;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenresDao genresDao;
    private final Scheduler jdbcScheduler;

    public GenreServiceImpl(GenresDao genresDao, Scheduler jdbcScheduler) {
        this.genresDao = genresDao;
        this.jdbcScheduler = jdbcScheduler;
    }

    @Override
    public Flux<Genre> getAll() {
        Flux<Genre> defer = Flux.defer(() -> Flux.fromIterable(this.genresDao.findAll()));
        return defer.subscribeOn(jdbcScheduler);
    }
}
