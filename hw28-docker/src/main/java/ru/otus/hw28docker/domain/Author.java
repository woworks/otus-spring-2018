package ru.otus.hw28docker.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    public Author() {
    }

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}