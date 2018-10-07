package ru.otus.hw17webfluxangular.rest;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.hw17webfluxangular.domain.Author;
import ru.otus.hw17webfluxangular.domain.Book;
import ru.otus.hw17webfluxangular.domain.Genre;
import ru.otus.hw17webfluxangular.service.BookService;

import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BookControllerTest {

    private static final String AUTHOR = "Charles Dickens";
    private static final String TITLE = "Oliver Twist";
    private static final String NEW_TITLE = "Oliver Twist Updated";
    private static final String GENRE = "Social novel";

    Book book1;
    Book book2;
    Book book3;

    private static Flux<Book> books;

    private WebClient webClient;

    @LocalServerPort
    private int serverPort;

    @MockBean
    private BookService bookService;
    private final WebTestClient client = WebTestClient.bindToController(new BookController(bookService))
            .configureClient()
            .baseUrl("/api/books")
            .build();

    @BeforeEach
    public void setUp() {

        setupBooks();

        this.webClient = WebClient.create("http://localhost:" + serverPort);
        books = Flux.just(book1, book2, book3);

    }

    @Test
    void testListBooks() {
        // given
        given(bookService.findAll())
                .willReturn(books);

        // when
        Flux<Book> receivedFlux = webClient.get().uri("/api/books").accept(MediaType.TEXT_EVENT_STREAM)
                .exchange().flatMapMany(response -> response.bodyToFlux(Book.class));

        // then
        StepVerifier.create(receivedFlux)
                .expectNext(book1)
                .expectNext(book2)
                .expectNext(book3)
                .expectComplete()
                .verify();
    }

    @Test
    void testEditBooks() {
        // given
        given(bookService.findById(2))
                .willReturn(Mono.just(book2));

        // when
        Mono<Book> receivedMono = webClient.get().uri("/api/books/2").accept(MediaType.APPLICATION_JSON)
                .exchange().flatMap(response -> response.bodyToMono(Book.class));

        // then
        StepVerifier.create(receivedMono)
                .expectNext(book2)
                .expectComplete()
                .verify();
    }


    private void setupBooks() {
        Author author1 = new Author(AUTHOR);
        Genre genre1 = new Genre(GENRE);

        book1 = new Book();
        book1.setTitle(TITLE);
        book1.setAuthor(author1);
        book1.setGenres(Sets.newHashSet(genre1));

        Book book1updated = new Book();
        book1updated.setTitle(NEW_TITLE);

        book2 = new Book();
        book2.setTitle("Cool Book2");
        book3 = new Book();
        book3.setTitle("Cool Book3");
    }
}