package ru.otus.hw17webfluxangular.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw17webfluxangular.repository.AuthorsDao;
import ru.otus.hw17webfluxangular.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorsDao authorsDao;
    private final Scheduler jdbcScheduler;

    public AuthorServiceImpl(AuthorsDao authorsDao, Scheduler jdbcScheduler) {
        this.authorsDao = authorsDao;
        this.jdbcScheduler = jdbcScheduler;
    }

    @Override
    public Flux<Author> getAll() {
        Flux<Author> defer = Flux.defer(() -> Flux.fromIterable(this.authorsDao.findAll()));
        return defer.subscribeOn(jdbcScheduler);
    }

    @Override
    public void insert(Author author) {
        this.authorsDao.save(author);
    }
}
