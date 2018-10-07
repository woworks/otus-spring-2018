package ru.otus.hw17webfluxangular.service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.hw17webfluxangular.repository.AuthorsDao;
import ru.otus.hw17webfluxangular.repository.BooksDao;
import ru.otus.hw17webfluxangular.repository.GenresDao;
import ru.otus.hw17webfluxangular.domain.Author;
import ru.otus.hw17webfluxangular.domain.Book;
import ru.otus.hw17webfluxangular.domain.Genre;

import java.sql.SQLException;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private final BooksDao booksDao;
    private final AuthorsDao authorsDao;
    private final GenresDao genresDao;
    private final Scheduler jdbcScheduler;

    @Autowired
    public BookServiceImpl(BooksDao booksDao, AuthorsDao authorsDao, GenresDao genresDao, Scheduler jdbcScheduler) {
        this.booksDao = booksDao;
        this.authorsDao = authorsDao;
        this.genresDao = genresDao;
        this.jdbcScheduler = jdbcScheduler;
    }


    @Override
    public Flux<Book> findAll() {
        Flux<Book> defer = Flux.defer(() -> Flux.fromIterable(enrichBooks(this.booksDao.findAllOrderByIdAsc())));
        return defer.subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Book> findById(long id) {
        Mono<Book> defer = Mono.defer(() -> Mono.just(this.booksDao.findById(id).get()));
        return defer.subscribeOn(jdbcScheduler);
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return this.booksDao.findByAuthor(author);
    }

    @Override
    public List<Book> getBooksByAuthor(String name) throws SQLException {
        Optional<Author> author = this.authorsDao.getByName(name);

        if (!author.isPresent()) {
            return Collections.emptyList();
        }

        List<Book> books = this.booksDao.findByAuthor(author.get());
        return this.enrichBooks(books);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return this.booksDao.findByGenres(genre);
    }

    @Override
    public List<Book> getBooksByGenre(String name) throws SQLException {
        Optional<Genre> genre = this.genresDao.getByName(name);

        if (!genre.isPresent()) {
            return Collections.emptyList();
        }

        List<Book> books = this.booksDao.findByGenres(genre.get());
        return this.enrichBooks(books);
    }

    @Override
    public Optional<Book> getBookByTitle(String name) {
        return this.booksDao.findByTitle(name);
    }


    @Override
    public void insert(Book book, String authorName, List<String> genreNames) {

        try {
            Optional<Author> authorOpt = this.authorsDao.getByName(authorName);
            if (authorOpt.isPresent()) {
                book.setAuthor(authorOpt.get());
            } else {
                Author a = new Author(authorName);
                this.authorsDao.save(a);
                book.setAuthor(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Set<Genre> genreList = new HashSet<>();
        for (String genreName : genreNames) {
            try {
                Optional<Genre> genreOpt = this.genresDao.getByName(genreName);
                if (genreOpt.isPresent()) {
                    genreList.add(genreOpt.get());
                } else {
                    Genre g = new Genre(genreName);
                    this.genresDao.save(g);
                    genreList.add(g);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        book.setGenres(genreList);

        this.booksDao.save(book);
    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public Book save(Long id, String title, Long authorId, List<Long> genres) {

        Book book = new Book();
        if (id != null) {
            Optional<Book> bookFromDb = this.booksDao.findById(id);
            if (bookFromDb.isPresent()){
                book = bookFromDb.get();
            }
        }

        book.setTitle(title);
        book.setAuthor(this.authorsDao.findById(authorId).orElse(null));
        Set<Genre> genresList = new HashSet<>();

        if (genres!= null && !genres.isEmpty()) {
            Iterable<Genre> genresIterable = this.genresDao.findAllById(genres);
            if (Iterables.size(genresIterable) > 0) {
                genresList = Sets.newHashSet(genresIterable);
            }
        }
        book.setGenres(genresList);
        return this.booksDao.save(book);
    }

    @Override
    public Mono<Book> save(Book book) {
        Mono<Book> defer = Mono.defer(() -> Mono.just(this.booksDao.save(book)));
        return defer.subscribeOn(jdbcScheduler);
    }

    private List<Book> enrichBooks(List<Book> books) {
        for (Book book : books) {
            book.setAuthor(this.authorsDao.getAuthorByBook(book).get());
            book.setGenres(this.genresDao.getGenresByBook(book));
        }

        return books;
    }
}
