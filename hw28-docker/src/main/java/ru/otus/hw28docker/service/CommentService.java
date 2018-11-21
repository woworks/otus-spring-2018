package ru.otus.hw28docker.service;

import ru.otus.hw28docker.domain.Book;
import ru.otus.hw28docker.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(Book book);

    void insert(String bookTitle, String userName, String comment);
}
