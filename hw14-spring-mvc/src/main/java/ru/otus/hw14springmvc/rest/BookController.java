package ru.otus.hw14springmvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw14springmvc.domain.Author;
import ru.otus.hw14springmvc.domain.Book;
import ru.otus.hw14springmvc.domain.Genre;
import ru.otus.hw14springmvc.service.AuthorService;
import ru.otus.hw14springmvc.service.BookService;
import ru.otus.hw14springmvc.service.GenreService;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam(value = "id", required=false) Long id, Model model) {
        Book book = null;
        if (id == null) {
            book = new Book();
        } else {
            book = bookService.findById(id).orElseThrow(NotFoundException::new);
        }

        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String editPage(Long id, String title, Long author,
                           @RequestParam(value = "genres", required=false) List<Long> genres , Model model) {

        this.bookService.save(id, title, author, genres);

        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @ModelAttribute("allAuthors")
    public List<Author> populateAuthors() {
        return this.authorService.getAll();
    }

    @ModelAttribute("allGenres")
    public List<Genre> populateGenres() {
        return this.genreService.getAll();
    }
}
