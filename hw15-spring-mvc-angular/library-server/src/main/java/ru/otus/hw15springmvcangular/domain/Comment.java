package ru.otus.hw15springmvcangular.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String text;

    @ManyToOne
    private Book book;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
