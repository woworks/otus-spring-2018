package ru.otus.hw6daospringjdbc.domain;

import java.util.List;

public class Book {
    private long id;
    private String title;
    private List<Genre> genres;
    private Author author;


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
