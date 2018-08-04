package ru.otus.hw10springdatajpa.service;

import ru.otus.hw10springdatajpa.domain.Book;
import ru.otus.hw10springdatajpa.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(Book book);

    void insert(String bookTitle, String userName, String comment);
}
