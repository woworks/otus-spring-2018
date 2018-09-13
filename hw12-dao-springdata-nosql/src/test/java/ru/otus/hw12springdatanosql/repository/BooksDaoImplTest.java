package ru.otus.hw12springdatanosql.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw12springdatanosql.domain.Book;
import ru.otus.hw12springdatanosql.domain.Comment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@ComponentScan("ru.otus.hw12springdatajpa.repository")
class BooksDaoImplTest {

    private static final String AUTHOR1 = "Charles Dickens";
    private static final String AUTHOR2 = "Leo Tolstoy";
    private static final String TITLE1 = "Oliver Twist";
    private static final String TITLE2 = "War and peace";
    private static final String GENRE1 = "Social novel";
    private static final String GENRE2 = "Novel";
    private static final String GENRE3 = "Comedy";

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp(){
        this.bookRepository.deleteAll();
    }

    @Test
    void getBookByTitle() {

        Book book1 = new Book();
        book1.setTitle(TITLE1);
        book1.setAuthor(AUTHOR1);
        book1.setGenres(Collections.singletonList(GENRE1));

        Book book2 = this.bookRepository.save(book1);

        List<Book> book3 = this.bookRepository.findByTitle(TITLE1);

        assertEquals(book1, book2);
        assert book3.size() == 1;
        assertEquals(book1, book3.get(0));
    }


    @Test
    void testFindAll(){
        Book book1 = new Book();
        book1.setTitle(TITLE1);
        book1.setAuthor(AUTHOR1);
        book1.setGenres(Collections.singletonList(GENRE1));
        this.bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle(TITLE2);
        book2.setAuthor(AUTHOR2);
        book2.setGenres(Collections.singletonList(GENRE2));
        this.bookRepository.save(book2);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);


        List<Book> books = this.bookRepository.findAll();

        assert books.size() == 2;
        assert books.containsAll(bookList);

    }

    @Test
    void testFindByGenres(){
        Book book1 = new Book();
        book1.setTitle(TITLE1);
        book1.setAuthor(AUTHOR1);
        book1.setGenres(Collections.singletonList(GENRE1));
        this.bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle(TITLE2);
        book2.setAuthor(AUTHOR2);
        book2.setGenres(Collections.singletonList(GENRE2));
        this.bookRepository.save(book2);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);


        List<Book> books = this.bookRepository.findByGenre(GENRE1);

        assert books.size() == 1;
        assert books.get(0).equals(book1);

    }

    @Test
    void testFindByAuthor(){
        Book book1 = new Book();
        book1.setTitle(TITLE1);
        book1.setAuthor(AUTHOR1);
        book1.setGenres(Collections.singletonList(GENRE1));
        this.bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle(TITLE2);
        book2.setAuthor(AUTHOR2);
        book2.setGenres(Collections.singletonList(GENRE2));
        this.bookRepository.save(book2);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);


        List<Book> books = this.bookRepository.findByAuthor(AUTHOR1);

        assert books.size() == 1;
        assert books.get(0).equals(book1);

    }


    @Test
    void testAddComment(){

        Book book1 = new Book();
        book1.setTitle(TITLE1);
        book1.setAuthor(AUTHOR1);
        book1.setGenres(Collections.singletonList(GENRE1));

        Comment comment = new Comment();
        comment.setUser("User1");
        comment.setText("Text1");

        book1.getComments().add(comment);

        this.bookRepository.save(book1);

        List<Book> books = this.bookRepository.findByAuthor(AUTHOR1);

        assert books.size() == 1;
        assert books.get(0).getComments().size() == 1;
        assert books.get(0).getComments().get(0).getText().equals("Text1");
        assert books.get(0).getComments().get(0).getUser().equals("User1");

    }


}