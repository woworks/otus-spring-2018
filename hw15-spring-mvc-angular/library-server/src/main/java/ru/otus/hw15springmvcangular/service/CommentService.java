package ru.otus.hw15springmvcangular.service;

import ru.otus.hw15springmvcangular.domain.Book;
import ru.otus.hw15springmvcangular.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(Book book);

    void insert(String bookTitle, String userName, String comment);
}
