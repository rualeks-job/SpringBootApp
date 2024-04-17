package ru.rybakov.spring.boot.SpringBootApp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @NotEmpty(message = "FIO should not be empty")
    @Size(min = 2, max = 30, message = "FIO should be between 2 and 30 characters")
    @Column(name = "user_fio")
    private String user_fio;
    @Column(name = "user_birthday")
    private String user_birthday;

    @OneToMany(mappedBy = "book_person")
    private List<Book> books;

    public Person(String user_fio, String user_birthday) {
        this.user_fio = user_fio;
        this.user_birthday = user_birthday;
    }

    public Person() {
    }

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getFIO() {
        return user_fio;
    }

     public List<Book> getBooks() {return books;}


     public void setBooks(List<Book> books) {this.books = books;}

    public void setFIO(String FIO) {
        this.user_fio = FIO;
    }

    public String getBirthday() {
        return user_birthday;
    }

    public void setBirthday(String birthday) {
        this.user_birthday = birthday;
    }
}
