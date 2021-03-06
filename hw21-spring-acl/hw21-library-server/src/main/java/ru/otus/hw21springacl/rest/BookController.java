package ru.otus.hw21springacl.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw21springacl.domain.Book;
import ru.otus.hw21springacl.service.BookService;

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
    public List<Book> listBooks() {
         return bookService.findAll();
    }

    @GetMapping("api/books/{id}")
    public Book editBooks(@PathVariable Long id) {
            return bookService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "api/books/{id}")
    public Book editBook(@PathVariable Long id, @RequestBody Book book) {
        Book returnBook = this.bookService.save(book);
        return returnBook;
    }
}
