package ru.rybakov.spring.boot.SpringBootApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rybakov.spring.boot.SpringBootApp.model.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
