package ru.otus.hw23springbatch.service;

import ru.otus.hw23springbatch.domain.Book;
import ru.otus.hw23springbatch.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(Book book);

    void insert(String bookTitle, String userName, String comment);
}
