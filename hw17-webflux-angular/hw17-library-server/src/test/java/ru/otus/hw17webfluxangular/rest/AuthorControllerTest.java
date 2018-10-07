package ru.otus.hw17webfluxangular.rest;

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
import reactor.test.StepVerifier;
import ru.otus.hw17webfluxangular.domain.Author;
import ru.otus.hw17webfluxangular.service.AuthorService;
import ru.otus.hw17webfluxangular.service.BookService;

import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AuthorControllerTest {

    Author author1 = new Author("Author1");
    Author author2 = new Author("Author2");
    Author author3 = new Author("Author3");

    private static Flux<Author> authors;

    private WebClient webClient;

    @LocalServerPort
    private int serverPort;

    @MockBean
    private AuthorService authorService;

    private final WebTestClient client = WebTestClient.bindToController(new AuthorController(authorService))
            .configureClient()
            .baseUrl("/api/books")
            .build();

    @BeforeEach
    public void setUp() {
        this.webClient = WebClient.create("http://localhost:" + serverPort);
        authors = Flux.just(author1, author2, author3);

    }

    @Test
    void testListAuthors() {
        // given
        given(authorService.getAll())
                .willReturn(authors);

        // when
        Flux<Author> receivedFlux = webClient.get().uri("/api/authors").accept(MediaType.TEXT_EVENT_STREAM)
                .exchange().flatMapMany(response -> response.bodyToFlux(Author.class));

        // then
        StepVerifier.create(receivedFlux)
                .expectNext(author1)
                .expectNext(author2)
                .expectNext(author3)
                .expectComplete()
                .verify();
    }
}