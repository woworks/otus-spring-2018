package ru.otus.hw21springacl.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw21springacl.domain.Genre;
import ru.otus.hw21springacl.service.GenreService;

import java.util.List;

@RestController
@CrossOrigin
public class GenreController {

    private final GenreService genreService;
    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("api/genres")
    @ResponseBody
    public List<Genre> listAuthors() {
        return this.genreService.getAll();
    }
}
