package ru.otus.hw8springormjpa.repository;

import ru.otus.hw8springormjpa.domain.Book;
import ru.otus.hw8springormjpa.domain.Comment;

import java.util.List;

public interface CommentsDao {

    void insert(Comment comment);

    List<Comment> getCommentsByBook(Book book);

}
