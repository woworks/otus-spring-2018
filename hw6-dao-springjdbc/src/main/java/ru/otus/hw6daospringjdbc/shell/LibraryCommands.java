package ru.otus.hw6daospringjdbc.shell;

import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw6daospringjdbc.domain.Author;
import ru.otus.hw6daospringjdbc.domain.Book;
import ru.otus.hw6daospringjdbc.domain.Genre;
import ru.otus.hw6daospringjdbc.service.AuthorService;
import ru.otus.hw6daospringjdbc.service.BookService;
import ru.otus.hw6daospringjdbc.service.GenreService;

import java.io.Console;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


@ShellComponent
public class LibraryCommands {

   private final BookService bookService;
   private final GenreService genreService;
   private final AuthorService authorService;

   @Autowired
    public LibraryCommands(BookService bookService, GenreService genreService, AuthorService authorService) {
        this.bookService = bookService;
       this.genreService = genreService;
       this.authorService = authorService;
   }

    @ShellMethod("Display books [All, By Author, By Genre] and Insert")
    public void books(@ShellOption(defaultValue = "list") String booksCommand, @ShellOption(defaultValue = "") String booksArgument) throws Exception {
        switch (booksCommand) {
            case "list":
                System.out.println("Listing books [" +  this.bookService.count() +  "]:");
                this.listBooks(this.bookService.getAll());
                break;
            case "genre":
                System.out.println("List books which have genre " + booksArgument);
                this.listByGenre(booksArgument);
                break;
            case "author":
                System.out.println("List books which have author " + booksArgument);
                this.listByAuthor(booksArgument);
                break;
            case "insert":
                System.out.println("Please input new book information");
                this.inserNewBook();
                break;
            default:
                System.out.println("Please input correct command (list, genre, author)");
        }
    }

    @ShellMethod("Display genres")
    public void genres() {
        System.out.printf("%5s| %30s\n", "Id", "Genre name");
        System.out.println("---------------------------------------------");
        this.genreService.getAll().forEach(genre -> System.out.printf("%5s| %30s\n",
                genre.getId(), genre.getName()));
    }

    @ShellMethod("Display Authors")
    public void authors() {
        System.out.printf("%5s| %30s\n", "Id", "Author name");
        System.out.println("---------------------------------------------");
        this.authorService.getAll().forEach(author -> System.out.printf("%5s| %30s\n",
                author.getId(), author.getName()));
    }

    @ShellMethod("Insert Author")
    public void insertauthor(@ShellOption(defaultValue = "") String authorName) {
       if (authorName.isEmpty()) {
            System.out.println("Author name is empty! Please try once again.");
            return;
        }
        this.authorService.insert(authorName);
    }

    private void listBooks(List<Book> books) {
        System.out.printf("%5s| %50s| %40s| %40s\n",
                "Id", "Title", "Genres", "Author");
        books.forEach(book -> System.out.printf("%5s| %50s| %40s| %40s\n",
                book.getId(), book.getTitle(), String.join(",", book.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toList())), book.getAuthor().getName()));
    }

    private void listByAuthor(String author){
        if (author.isEmpty()){
            System.out.println("Author cannot be  empty. Try once again.");
        }

        try {
            List<Book> booksByAuthor = this.bookService.getBooksByAuthor(author);
            System.out.println("Found " + booksByAuthor.size() + " books");
            this.listBooks(booksByAuthor);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void listByGenre(String genre){
        if (genre.isEmpty()){
            System.out.println("Genre cannot be  empty. Try once again.");
        }

        try {
            List<Book> booksByGenre = this.bookService.getBooksByGenre(genre);
            System.out.println("Found " + booksByGenre.size() + " books");
            this.listBooks(booksByGenre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void inserNewBook() throws Exception {

        Scanner scanner = new Scanner(System.in);

        if (scanner == null) {
            throw new Exception("Scanner is not available");
        }

        Book newBook = new Book();

        System.out.println("Please input Book Name: ");
        String bookName = scanner.nextLine();
        System.out.println("Please input Book's genres (comma separated): ");
        String bookGenres = scanner.nextLine();
        System.out.println("Please input Book's author: ");
        String bookAuthor = scanner.nextLine();

        newBook.setTitle(bookName);
        List<Genre> genres = Arrays.asList(bookGenres.split(","))
                .stream()
                .map(name -> new Genre(name))
                .collect(Collectors.toList());
        newBook.setGenres(genres);
        newBook.setAuthor(new Author(bookAuthor));

        this.bookService.insert(newBook);

    }



}
