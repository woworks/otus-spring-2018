package ru.otus.hw23springbatch.shell;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw23springbatch.domain.Author;
import ru.otus.hw23springbatch.domain.Book;
import ru.otus.hw23springbatch.domain.Comment;
import ru.otus.hw23springbatch.service.*;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@ShellComponent
public class LibraryCommands {

    @Autowired
    JobLauncher jobLauncher;

    private final BookService bookService;
    private final MongoBooksService mongoBooksService;
    private final CommentService commentService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final Job job;

    @Autowired
    public LibraryCommands(BookService bookService, CommentService commentService,
                           GenreService genreService, AuthorService authorService,
                           MongoBooksService mongoBooksService,
                           Job job) {
        this.bookService = bookService;
        this.commentService = commentService;
        this.genreService = genreService;
        this.authorService = authorService;
        this.mongoBooksService = mongoBooksService;
        this.job = job;

    }

    @ShellMethod("Display books [All, By Author, By Genre] and Insert")
    public void books(@ShellOption(defaultValue = "list") String booksCommand, @ShellOption(defaultValue = "") String booksArgument) {
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
            case "save":
                System.out.println("Please input new book information");
                this.inserNewBook();
                break;
            default:
                System.out.println("Please input correct command (list, genre, author)");
        }
    }

    @ShellMethod("Migrate books from Postgres to Mongo")
    public void migrate() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        this.mongoBooksService.purge();
        this.jobLauncher.run(this.job, new JobParameters());
    }

    @ShellMethod("Migrate books from Postgres to Mongo")
    public void mongobooks() {
        this.mongoBooksService.getAll().forEach(System.out::println);
    }

    @ShellMethod("Display comments for a book or save a comment [list, save]")
    public void comments(@ShellOption(defaultValue = "list") String commentCommand, @ShellOption String bookTitle) {
        switch (commentCommand) {
            case "list":
                System.out.println("Listing comments for [" +  bookTitle +  "]:");
                this.listComments(bookTitle);
                break;
            case "save":
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

        System.out.println("Please input username: ");
        String username = scanner.nextLine();
        while (username.isEmpty()) {
            System.out.println("username is empty! Write once again!");
            username = scanner.nextLine();
        }

        System.out.println("Commment: ");
        String comment = scanner.nextLine();

        this.commentService.insert(bookTitle, username, comment);
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
        Author newAuthor = new Author();
       newAuthor.setName(authorName);
        this.authorService.insert(newAuthor);
    }

    private void listBooks(List<Book> books) {
        System.out.printf("%5s| %50s| %40s| %40s\n",
                "Id", "Title", "Genres", "Author");
        books.forEach(book -> System.out.printf("%5s| %50s| %40s| %40s\n",
                book.getId(), book.getTitle(), String.join(",", book.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toList())), book.getAuthor().getName()));
    }

    private void listComments(String bookName) {

        List<Comment> comments = new ArrayList<>();
        Optional<Book> book = this.bookService.getBookByName(bookName);
        if (!book.isPresent()){
            System.out.println("No book with this name found. Please try anothe name.");
            return;
        } else {
            comments = this.commentService.getCommentsByBook(book.get());
        }



        System.out.printf("%5s| %50s| %40s\n",
                "Id", "Username", "Comment");
        comments.forEach(comment -> System.out.printf("%5s| %50s| %40s\n",
                comment.getId(),comment.getUser().getUsername(), comment.getText()));
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

    private void inserNewBook() {

        Scanner scanner = new Scanner(System.in);

        Book newBook = new Book();

        System.out.println("Please input Book Name: ");
        String bookName = scanner.nextLine();
        System.out.println("Please input Book's genres (comma separated): ");
        String bookGenres = scanner.nextLine();
        System.out.println("Please input Book's author: ");
        String bookAuthor = scanner.nextLine();

        newBook.setTitle(bookName);
        List<String> genres = Arrays.asList(bookGenres.split(","));

        this.bookService.insert(newBook, bookAuthor, genres);

    }



}
