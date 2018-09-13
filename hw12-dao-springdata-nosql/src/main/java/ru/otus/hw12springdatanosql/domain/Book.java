package ru.otus.hw12springdatanosql.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Document(collection="books")
public class Book {
    @Id
    private String id;

    @NotBlank
    @Size(max=255)
    @Indexed(unique=true)
    @Field("title")
    private String title;

    @NotBlank
    @Size(max=100)
    @Field("author")
    private String author;

    @Field("genres")
    private List<String> genres;

    @Field("comments")
    private List<Comment> comments = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!id.equals(book.id)) return false;
        if (!title.equals(book.title)) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (genres != null ? !genres.equals(book.genres) : book.genres != null) return false;
        return comments != null ? comments.equals(book.comments) : book.comments == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
