package ru.otus.hw14springmvc.service;

import ru.otus.hw14springmvc.domain.Book;
import ru.otus.hw14springmvc.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(Book book);

    void insert(String bookTitle, String userName, String comment);
}
