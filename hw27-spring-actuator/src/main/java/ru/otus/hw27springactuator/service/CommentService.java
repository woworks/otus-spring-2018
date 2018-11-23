package ru.otus.hw27springactuator.service;

import ru.otus.hw27springactuator.domain.Book;
import ru.otus.hw27springactuator.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(Book book);

    void insert(String bookTitle, String userName, String comment);
}
