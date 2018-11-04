package ru.otus.hw21springacl.service;

import ru.otus.hw21springacl.domain.Book;
import ru.otus.hw21springacl.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(Book book);

    void insert(String bookTitle, String userName, String comment);
}
