package ru.otus.hw17webfluxangular.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.hw17webfluxangular.domain.Genre;
import ru.otus.hw17webfluxangular.service.GenreService;

@RestController
@CrossOrigin
public class GenreController {

    private final GenreService genreService;
    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("api/genres")
     public Flux<Genre> listGenres() {
        return this.genreService.getAll();
    }
}
