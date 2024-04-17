package ru.rybakov.spring.boot.SpringBootApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rybakov.spring.boot.SpringBootApp.model.Book;
import ru.rybakov.spring.boot.SpringBootApp.model.Person;
import ru.rybakov.spring.boot.SpringBootApp.repositories.BookRepository;

import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    };

    public Book findOne(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public void save(Book book){
        bookRepository.save(book);
    }

    public void update(int id, Book updatedBook){
        updatedBook.setBook_id(id);
        bookRepository.save(updatedBook);
    }

    public void delete(int id){
        bookRepository.deleteById(id);
    }

    public Person getPerson(int id){
        Book book = bookRepository.findById(id).orElse(null);
        return book.getBook_person();
    }

    public void giveBook(int id, Person person) {
        Book book = bookRepository.findById(id).orElse(null);
        book.setBook_person(person);
        bookRepository.save(book);
    }

    public void returnBook(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        book.setBook_person(null);
    }

}
