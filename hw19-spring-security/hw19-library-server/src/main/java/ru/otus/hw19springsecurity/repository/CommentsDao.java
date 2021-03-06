package ru.otus.hw19springsecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw19springsecurity.domain.Book;
import ru.otus.hw19springsecurity.domain.Comment;

import java.util.List;

@Repository
public interface CommentsDao extends CrudRepository<Comment,Long> {

    List<Comment> getCommentsByBook(Book book);

}
