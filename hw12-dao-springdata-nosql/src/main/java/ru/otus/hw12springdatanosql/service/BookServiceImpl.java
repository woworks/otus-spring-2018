package ru.otus.hw12springdatanosql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw12springdatanosql.domain.Book;
import ru.otus.hw12springdatanosql.domain.Comment;
import ru.otus.hw12springdatanosql.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Book create(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        this.bookRepository.delete(book);
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public long count() {
        return this.bookRepository.count();
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return this.bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        return this.bookRepository.findByGenres(genre);
    }

    @Override
    public Optional<Book> getBookByTitle(String title) {
        return this.bookRepository.findByTitle(title);
    }

    @Override
    public void addComment(String title, String username, String text) {
        Optional<Book> books = this.getBookByTitle(title);
        Book book = books.get();
        Comment comment = new Comment();
        comment.setUser(username);
        comment.setText(text);
        book.getComments().add(comment);

        this.bookRepository.save(book);
    }
}
