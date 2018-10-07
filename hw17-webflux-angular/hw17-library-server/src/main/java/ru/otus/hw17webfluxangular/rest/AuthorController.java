package ru.otus.hw17webfluxangular.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.otus.hw17webfluxangular.domain.Author;
import ru.otus.hw17webfluxangular.service.AuthorService;

@RestController
@CrossOrigin
public class AuthorController {

    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("api/authors")
    public Flux<Author> listAuthors() {
        return this.authorService.getAll();
    }
}
