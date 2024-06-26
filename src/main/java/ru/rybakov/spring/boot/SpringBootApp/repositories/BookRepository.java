package ru.rybakov.spring.boot.SpringBootApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rybakov.spring.boot.SpringBootApp.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
