package ru.otus.hw28docker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw28docker.domain.Book;
import ru.otus.hw28docker.domain.Comment;

import java.util.List;

@Repository
public interface CommentsDao extends CrudRepository<Comment,Long> {

    List<Comment> getCommentsByBook(Book book);

}
