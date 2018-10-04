package ru.otus.hw15springmvcangular.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw15springmvcangular.domain.Book;
import ru.otus.hw15springmvcangular.domain.Comment;

import java.util.List;

@Repository
public interface CommentsDao extends CrudRepository<Comment,Long> {

    List<Comment> getCommentsByBook(Book book);

}
