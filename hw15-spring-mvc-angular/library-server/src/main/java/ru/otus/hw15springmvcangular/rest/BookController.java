package ru.otus.hw15springmvcangular.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw15springmvcangular.domain.Book;
import ru.otus.hw15springmvcangular.service.BookService;

import java.util.List;

@RestController
@CrossOrigin
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("api/books")
    @ResponseBody
    public List<Book> listPage() {
         return bookService.findAll();
    }

    @GetMapping("api/books/{id}")
    @ResponseBody
    public Book editPage(@PathVariable Long id) {
            return bookService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "api/books/{id}")
    @ResponseBody
    public Book editPage(@PathVariable Long id, @RequestBody Book book) {
        Book returnBook = this.bookService.save(book);
        return returnBook;
    }
}
