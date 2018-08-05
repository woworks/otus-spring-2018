package ru.otus.hw8springormjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw8springormjpa.repository.AuthorsDao;
import ru.otus.hw8springormjpa.repository.BooksDao;
import ru.otus.hw8springormjpa.repository.GenresDao;
import ru.otus.hw8springormjpa.domain.Author;
import ru.otus.hw8springormjpa.domain.Book;
import ru.otus.hw8springormjpa.domain.Genre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public List<Book> getBooksByAuthor(Author author) {
        return this.booksDao.getBooksByAuthor(author);
    }

    @Override
    public List<Book> getBooksByAuthor(String name) throws SQLException {
        Optional<Author> author = this.authorsDao.getByName(name);

        if (!author.isPresent()) {
            return Collections.emptyList();
        }

        List<Book> books = this.booksDao.getBooksByAuthor(author.get());
        return this.enrichBooks(books);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return this.booksDao.getBooksByGenre(genre);
    }

    @Override
    public List<Book> getBooksByGenre(String name) throws SQLException {
        Optional<Genre> genre = this.genresDao.getByName(name);

        if (!genre.isPresent()) {
            return Collections.emptyList();
        }

        List<Book> books = this.booksDao.getBooksByGenre(genre.get());
        return this.enrichBooks(books);
    }

    @Override
    public Optional<Book> getBookByName(String name) {
        return this.booksDao.getBookByName(name);
    }


    @Override
    public void insert(Book book, String authorName, List<String> genreNames) {

        try {
            Optional<Author> authorOpt = this.authorsDao.getByName(authorName);
            if (authorOpt.isPresent()) {
                book.setAuthor(authorOpt.get());
            } else {
                Author a = new Author(authorName);
                this.authorsDao.insert(a);
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
                    this.genresDao.insert(g);
                    genreList.add(g);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        book.setGenres(genreList);

        this.booksDao.insert(book);
    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public Book update(Book book) {
        return null;
    }

    private List<Book> enrichBooks(List<Book> books) {
        for (Book book : books) {
            book.setAuthor(this.authorsDao.getAuthorByBook(book).get());
            book.setGenres(this.genresDao.getGenresByBook(book));
        }

        return books;
    }
}
