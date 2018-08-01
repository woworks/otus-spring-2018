package ru.otus.hw8springormjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw8springormjpa.domain.*;
import ru.otus.hw8springormjpa.repository.*;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final BooksDao booksDao;
    private final UsersDao usersDao;
    private final CommentsDao commentsDao;

    @Autowired
    public CommentServiceImpl(BooksDao booksDao, CommentsDao commentsDao, UsersDao usersDao) {
        this.booksDao = booksDao;
        this.commentsDao = commentsDao;
        this.usersDao = usersDao;
    }

    @Override
    public List<Comment> getCommentsByBook(Book book) {
        return this.commentsDao.getCommentsByBook(book);
    }

    @Override
    @Transactional
    public void insert(String bookTitle, String userName, String commentText) {

        Comment comment = new Comment();
        comment.setText(commentText);

        Optional<Book> bookOpt = this.booksDao.getBookByName(bookTitle);
        if (bookOpt.isPresent()) {
            comment.setBook(bookOpt.get());
        } else {
            System.out.printf("There is no book with title %s", bookTitle);
            return;
        }


        Optional<User> userOpt = this.usersDao.getUserByUsername(userName);
        if (userOpt.isPresent()) {
            comment.setUser(userOpt.get());
        } else {
            User user = new User();
            user.setUsername(userName);
            this.usersDao.insert(user);
            comment.setUser(user);
        }

        this.commentsDao.insert(comment);

        bookOpt.get().getComments().add(comment);
    }
}
