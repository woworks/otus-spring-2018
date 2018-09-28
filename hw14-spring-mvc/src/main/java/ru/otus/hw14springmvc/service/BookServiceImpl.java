package ru.otus.hw14springmvc.service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw14springmvc.repository.AuthorsDao;
import ru.otus.hw14springmvc.repository.BooksDao;
import ru.otus.hw14springmvc.repository.GenresDao;
import ru.otus.hw14springmvc.domain.Author;
import ru.otus.hw14springmvc.domain.Book;
import ru.otus.hw14springmvc.domain.Genre;

import java.sql.SQLException;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private final BooksDao booksDao;
    private final AuthorsDao authorsDao;
    private final GenresDao genresDao;

    @Autowired
    public BookServiceImpl(BooksDao booksDao, AuthorsDao authorsDao, GenresDao genresDao) {
        this.booksDao = booksDao;
        this.authorsDao = authorsDao;
        this.genresDao = genresDao;
    }


    @Override
    public List<Book> findAll() {
        List<Book> bookList = Lists.newArrayList(this.booksDao.findAllOrderByIdAsc());
        return this.enrichBooks(bookList);
    }

    @Override
    public Optional<Book> findById(long id) {
        return this.booksDao.findById(id);
    }

    @Override
    public long count() {
        return this.booksDao.count();
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
        return this.booksDao.findByGenre(genre);
    }

    @Override
    public List<Book> getBooksByGenre(String name) throws SQLException {
        Optional<Genre> genre = this.genresDao.getByName(name);

        if (!genre.isPresent()) {
            return Collections.emptyList();
        }

        List<Book> books = this.booksDao.findByGenre(genre.get());
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

        List<Genre> genreList = new ArrayList<>();
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
            Optional<Book> bookFromDb = findById(id);
            if (bookFromDb.isPresent()){
                book = bookFromDb.get();
            }
        }

        book.setTitle(title);
        book.setAuthor(this.authorsDao.findById(authorId).orElse(null));
        List<Genre> genresList = new ArrayList<>();

        if (genres!= null && !genres.isEmpty()) {
            Iterable<Genre> genresIterable = this.genresDao.findAllById(genres);
            if (Iterables.size(genresIterable) > 0) {
                genresList = Lists.newArrayList(genresIterable);
            }
        }
        book.setGenres(genresList);
        return this.booksDao.save(book);
    }

    private List<Book> enrichBooks(List<Book> books) {
        for (Book book : books) {
            book.setAuthor(this.authorsDao.getAuthorByBook(book).get());
            book.setGenres(this.genresDao.getGenresByBook(book));
        }

        return books;
    }
}
