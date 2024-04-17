package ru.rybakov.spring.boot.SpringBootApp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int book_id;

    @Column(name = "book_title")
    @NotEmpty(message = "Title should not be empty")
    String book_title;

    @Column(name = "book_author")
    @NotEmpty(message = "Author should not be empty")
    String book_author;
    @Column(name = "book_year")
    String book_year;

    public Person getBook_person() {
        return book_person;
    }

    public void setBook_person(Person book_person) {
        this.book_person = book_person;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    Person book_person;


    public Book() {
    }

    public Book(String book_title, String book_author, String book_year) {


        this.book_title = book_title;
        this.book_author = book_author;
        this.book_year = book_year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_year() {
        return book_year;
    }

    public void setBook_year(String book_year) {
        this.book_year = book_year;
    }
}
