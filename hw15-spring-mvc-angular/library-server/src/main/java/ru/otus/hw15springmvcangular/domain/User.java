package ru.otus.hw15springmvcangular.domain;


import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
