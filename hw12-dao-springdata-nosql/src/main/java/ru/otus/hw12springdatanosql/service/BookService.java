package ru.otus.hw12springdatanosql.service;

import org.springframework.stereotype.Service;
import ru.otus.hw12springdatanosql.domain.Book;

import java.util.List;

@Service
public interface BookService {

    Book create(Book book);

    Book update(Book book);

    void delete(Book book);

    List<Book> getAll();

    long count();

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByGenre(String genre);

    List<Book> getBookByTitle(String name);

    void addComment(String bookTitle, String username, String comment);
}
