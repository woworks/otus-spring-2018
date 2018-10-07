package ru.otus.hw17webfluxangular.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw17webfluxangular.domain.Book;
import ru.otus.hw17webfluxangular.domain.Comment;

import java.util.List;

@Repository
public interface CommentsDao extends CrudRepository<Comment,Long> {

    List<Comment> getCommentsByBook(Book book);

}
