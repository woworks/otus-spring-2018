package ru.otus.hw19springsecurity.service;

import ru.otus.hw19springsecurity.domain.Book;
import ru.otus.hw19springsecurity.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(Book book);

    void insert(String bookTitle, String userName, String comment);
}
