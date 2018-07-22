package ru.otus.hw6daospringjdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw6daospringjdbc.dao.AuthorsDao;
import ru.otus.hw6daospringjdbc.dao.BooksDao;
import ru.otus.hw6daospringjdbc.dao.GenresDao;
import ru.otus.hw6daospringjdbc.domain.Author;
import ru.otus.hw6daospringjdbc.domain.Book;
import ru.otus.hw6daospringjdbc.domain.Genre;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

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
    public List<Book> getAll() {
        List<Book> bookList = this.booksDao.getAll();
        return this.enrichBooks(bookList);

    }

    @Override
    public long count() {
        return this.booksDao.count();
    }

    @Override
    public Book getById(long id) throws SQLException {
            return this.booksDao.getById(id);
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return this.booksDao.getBooksByAuthor(author);
    }

    @Override
    public List<Book> getBooksByAuthor(String name) throws SQLException {
        Author author = this.authorsDao.getByName(name);

        if (author == null) {
            return Collections.emptyList();
        }

        List<Book> books = this.booksDao.getBooksByAuthor(author);
        return this.enrichBooks(books);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return this.booksDao.getBooksByGenre(genre);
    }

    @Override
    public List<Book> getBooksByGenre(String name) throws SQLException {
        Genre genre = this.genresDao.getByName(name);

        if (genre == null) {
            return Collections.emptyList();
        }

        List<Book> books = this.booksDao.getBooksByGenre(genre);
        return this.enrichBooks(books);
    }

    @Override
    public void insert(Book book) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public Book update(Book book) {
        return null;
    }

    private List<Book> enrichBooks(List<Book> books) {
        for (Book book: books){
            book.setAuthor(this.authorsDao.getAuthorByBookId(book.getId()));
            book.setGenres(this.genresDao.getGenresByBookId(book.getId()));
        }

        return books;
    }
}
