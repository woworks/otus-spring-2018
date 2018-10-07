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
import ru.otus.hw17webfluxangular.domain.Genre;
import ru.otus.hw17webfluxangular.service.GenreService;

import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class GenreControllerTest {

    Genre genre1 = new Genre("Genre1");
    Genre genre2 = new Genre("Genre2");
    Genre genre3 = new Genre("Genre3");

    private static Flux<Genre> genres;

    private WebClient webClient;

    @LocalServerPort
    private int serverPort;

    @MockBean
    private GenreService genreService;

    private final WebTestClient client = WebTestClient.bindToController(new GenreController(genreService))
            .configureClient()
            .baseUrl("/api/books")
            .build();

    @BeforeEach
    public void setUp() {
        this.webClient = WebClient.create("http://localhost:" + serverPort);
        genres = Flux.just(genre1, genre2, genre3);

    }

    @Test
    void testListGenres() {
        // given
        given(genreService.getAll())
                .willReturn(genres);

        // when
        Flux<Genre> receivedFlux = webClient.get().uri("/api/genres").accept(MediaType.TEXT_EVENT_STREAM)
                .exchange().flatMapMany(response -> response.bodyToFlux(Genre.class));

        // then
        StepVerifier.create(receivedFlux)
                .expectNext(genre1)
                .expectNext(genre2)
                .expectNext(genre3)
                .expectComplete()
                .verify();
    }
}