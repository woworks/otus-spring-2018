package ru.otus.hw15springmvcangular.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw15springmvcangular.domain.Author;
import ru.otus.hw15springmvcangular.domain.Book;
import ru.otus.hw15springmvcangular.domain.Genre;
import ru.otus.hw15springmvcangular.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    private static final String AUTHOR = "Charles Dickens";
    private static final String TITLE = "Oliver Twist";
    private static final String NEW_TITLE = "Oliver Twist Updated";
    private static final String GENRE = "Social novel";

    private static List<Book> books = new ArrayList<>();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    private JacksonTester<Book> jsonBook;

    @BeforeEach
    public void setUp() {

        JacksonTester.initFields(this, new ObjectMapper());

        books.clear();

        Author author1 = new Author(AUTHOR);
        Genre genre1 = new Genre(GENRE);

        Book book1 = new Book();
        book1.setTitle(TITLE);
        book1.setAuthor(author1);
        book1.setGenres(Sets.newHashSet(genre1));

        Book book1updated = new Book();
        book1updated.setTitle(NEW_TITLE);

        Book book2 = new Book();
        book2.setTitle("Cool Book2");
        Book book3 = new Book();
        book3.setTitle("Cool Book3");
        books.add(book1);
        books.add(book2);
        books.add(book3);

        Mockito.when(bookService.findAll())
                .thenReturn(books);

        Mockito.when(bookService.findById(1))
                .thenReturn(Optional.of(book1));

        Mockito.when(bookService.save(anyLong(), anyString(), anyLong(), anyList()))
                .then((Answer<Book>) var1 -> {
                    books.get(0).setTitle(NEW_TITLE);
                    return book1updated;
                });
    }

    @Test
    void listPage() throws Exception {
                mvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    void editPageGet() throws Exception {
        mvc.perform(get("/api/books").param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void editPagePost() throws Exception {
        mvc.perform(post("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBook.write(books.get(0)).getJson()))
                .andExpect(status().isOk());
    }
}