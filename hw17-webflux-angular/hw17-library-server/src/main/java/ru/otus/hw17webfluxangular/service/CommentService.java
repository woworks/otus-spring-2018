package ru.otus.hw17webfluxangular.service;

import ru.otus.hw17webfluxangular.domain.Book;
import ru.otus.hw17webfluxangular.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(Book book);

    void insert(String bookTitle, String userName, String comment);
}
