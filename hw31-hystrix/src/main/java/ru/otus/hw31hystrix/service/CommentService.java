package ru.otus.hw31hystrix.service;

import ru.otus.hw31hystrix.domain.Book;
import ru.otus.hw31hystrix.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(Book book);

    void insert(String bookTitle, String userName, String comment);
}
