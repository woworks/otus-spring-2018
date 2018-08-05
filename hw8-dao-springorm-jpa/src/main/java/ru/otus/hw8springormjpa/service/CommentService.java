package ru.otus.hw8springormjpa.service;

import ru.otus.hw8springormjpa.domain.Book;
import ru.otus.hw8springormjpa.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(Book book);

    void insert(String bookTitle, String userName, String comment);
}
