package ru.rybakov.spring.boot.SpringBootApp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rybakov.spring.boot.SpringBootApp.model.Book;
import ru.rybakov.spring.boot.SpringBootApp.model.Person;
import ru.rybakov.spring.boot.SpringBootApp.services.BookService;
import ru.rybakov.spring.boot.SpringBootApp.services.PeopleService;


@Controller
@RequestMapping("/books")
public class BookController {

    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public BookController( PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String getBook(Model model) {
        model.addAttribute("books", bookService.findAll());

        return "book/index";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("books", new Book());

        return "book/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("person") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        bookService.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("books", bookService.findOne(id));
        if (bookService.getPerson(id) == null) {
           Person person1 = new Person();
           person1.setFIO("No user");
            model.addAttribute("person", person1);
        } else {
            model.addAttribute("person", peopleService.findPerson(bookService.getPerson(id).getId()));
        }
        return "book/show";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id,
                           Model model) {

        model.addAttribute("book", bookService.findOne(id));

        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id,
                             @ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/edit";
        bookService.update(id, book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/give")
    public String giveBook(@PathVariable("id") int id,
                           @ModelAttribute("person") Person person,
                           Model model) {
        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("people", peopleService.findAll());

        return "book/give";
    }

    @PatchMapping("/{id}/given")
    public String givenBook(@PathVariable("id") int id,
                            @ModelAttribute("person") Person person,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/give";
        bookService.giveBook(id, person);

        return "redirect:/books";
    }
    @GetMapping("/{id}/return")
    public String returnBook(@PathVariable("id") int id) {
        bookService.returnBook(id);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

}
