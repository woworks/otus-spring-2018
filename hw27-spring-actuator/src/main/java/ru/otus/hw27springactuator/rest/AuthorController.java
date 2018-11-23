package ru.otus.hw27springactuator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw27springactuator.domain.Author;
import ru.otus.hw27springactuator.service.AuthorService;

import java.util.List;

@RestController
@CrossOrigin
public class AuthorController {

    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("api/authors")
    @ResponseBody
    public List<Author> listAuthors() {
        return this.authorService.getAll();
    }
}
