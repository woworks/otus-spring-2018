package ru.otus.hw12springdatanosql.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw12springdatanosql.domain.Book;
import ru.otus.hw12springdatanosql.service.BookService;

import java.util.*;


@ShellComponent
public class LibraryCommands {

    private final BookService bookService;

    @Autowired
    public LibraryCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("Display books [All, By Author, By Genre] and Insert")
    public void books(@ShellOption(defaultValue = "list") String booksCommand, @ShellOption(defaultValue = "") String argument) {
        switch (booksCommand) {
            case "list":
                System.out.println("Listing books [" +  this.bookService.count() +  "]:");
                this.listBooks(this.bookService.getAll());
                break;
            case "genre":
                System.out.println("List books which have genre " + argument);
                this.listByGenre(argument);
                break;
            case "author":
                System.out.println("List books which have author " + argument);
                this.listByAuthor(argument);
                break;
            case "save":
                System.out.println("Please input new book information");
                this.inserNewBook();
                break;
            default:
                System.out.println("Please input correct command (list, genre, author, save)");
        }
    }

    @ShellMethod("Display comments for a book or add a comment to a book [list, add]")
    public void comments(@ShellOption(defaultValue = "list") String commentCommand, @ShellOption String bookTitle) {
        switch (commentCommand) {
            case "list":
                System.out.println("Listing comments for [" +  bookTitle +  "]:");
                this.listComments(bookTitle);
                break;
            case "add":
                System.out.printf("Please input new comment for book [%s]:%n", bookTitle);
                this.insertNewComment(bookTitle);
                break;
            default:
                System.out.println("Please input correct command (list, save)");
        }
    }

    private void insertNewComment(String bookTitle) {
        Scanner scanner = new Scanner(System.in);

        if (bookTitle.isEmpty()){
            System.out.println("Book title is empty! Write once again!");
            return;
        }

        Optional<Book> book = this.bookService.getBookByTitle(bookTitle);
        if (!book.isPresent()){
            System.out.println("No books with title " + bookTitle);
            return;
        }

        System.out.println("Please input username: ");
        String username = scanner.nextLine();
        while (username.isEmpty()) {
            System.out.println("username is empty! Write once again!");
            username = scanner.nextLine();
        }

        System.out.println("Commment: ");
        String comment = scanner.nextLine();

        this.bookService.addComment(bookTitle, username, comment);
    }

    private void listBooks(List<Book> books) {

        if (books == null || books.isEmpty()){
            System.out.println("No books found");
            return;
        }

        System.out.printf("%30s| %50s| %40s| %40s %n",
                "Id", "Title", "Genres", "Author");
        books.forEach(book -> System.out.printf("%30s| %50s| %40s| %40s %n",
                book.getId(), book.getTitle(), String.join(",", book.getGenres()), book.getAuthor()));
    }

    private void listComments(String bookTitle) {


        Optional<Book> book = this.bookService.getBookByTitle(bookTitle);
        if (!book.isPresent()){
            System.out.println("No book with this title found. Please try another title.");
            return;
        }

        System.out.printf("%15s| %50s %n", "Username", "Comment");
        book.get().getComments().forEach(comment -> System.out.printf("%15s| %50s %n", comment.getUser(), comment.getText()));
    }

    private void listByAuthor(String author){
        if (author.isEmpty()){
            System.out.println("Author cannot be  empty. Try once again.");
        }


            List<Book> booksByAuthor = this.bookService.getBooksByAuthor(author);
            System.out.println("Found " + booksByAuthor.size() + " books");
            this.listBooks(booksByAuthor);
    }

    private void listByGenre(String genre){
        if (genre.isEmpty()){
            System.out.println("Genre cannot be  empty. Try once again.");
        }

            List<Book> booksByGenre = this.bookService.getBooksByGenre(genre);
            System.out.println("Found " + booksByGenre.size() + " books");
            this.listBooks(booksByGenre);

    }

    private void inserNewBook() {

        Scanner scanner = new Scanner(System.in);

        Book newBook = new Book();

        System.out.println("Please input Book Title: ");
        String bookTitle = scanner.nextLine();
        System.out.println("Please input Book's genres (comma separated): ");
        String bookGenres = scanner.nextLine();
        System.out.println("Please input Book's author: ");
        String bookAuthor = scanner.nextLine();

        newBook.setTitle(bookTitle);
        newBook.setAuthor(bookAuthor);
        List<String> genres = Arrays.asList(bookGenres.split(","));
        newBook.setGenres(genres);

        this.bookService.create(newBook);

    }



}
