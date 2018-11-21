package ru.otus.hw28docker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw28docker.domain.*;
import ru.otus.hw28docker.repository.*;

import javax.transaction.Transactional;
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

        Optional<Book> bookOpt = this.booksDao.findByTitle(bookTitle);
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
            this.usersDao.save(user);
            comment.setUser(user);
        }

        this.commentsDao.save(comment);

        bookOpt.get().getComments().add(comment);
    }
}
