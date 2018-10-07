package ru.otus.hw17webfluxangular.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw17webfluxangular.domain.Book;
import ru.otus.hw17webfluxangular.service.BookService;

@RestController
@CrossOrigin
public class BookController {

    Logger LOG = LoggerFactory.getLogger(BookController.class);


    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("api/books")
    public Flux<Book> listBooks() {
         return bookService.findAll();
    }

    @GetMapping("api/books/{id}")
    public Mono<Book> editBooks(@PathVariable Long id) {
            return bookService.findById(id)
                    .doOnError(NotFoundException.class, e -> LOG.error("Edit books error ", e.getMessage()));
    }

    @PostMapping(value = "api/books/{id}")
    public Mono<Book> editBook(@PathVariable Long id, @RequestBody Book book) {
        return this.bookService.save(book);
    }
}
