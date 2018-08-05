package ru.otus.hw8springormjpa.domain;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String title;

    @ManyToMany
    private List<Genre> genres;

    @OneToOne
    private Author author;

    @ManyToMany
    private List<Comment> comments;


    public Book() {
    }

    public Book(long id, String title, List<Genre> genres, Author author) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.author = author;
    }

    public Book(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", author=" + author +
                '}';
    }
}
