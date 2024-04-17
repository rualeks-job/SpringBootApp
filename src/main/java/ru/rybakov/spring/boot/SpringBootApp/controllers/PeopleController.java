package ru.rybakov.spring.boot.SpringBootApp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rybakov.spring.boot.SpringBootApp.model.Person;
import ru.rybakov.spring.boot.SpringBootApp.services.PeopleService;


@Controller
@RequestMapping("/readers")
public class PeopleController {


    private final PeopleService peopleService;

    @Autowired
    public PeopleController( PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id,
                         Model model){
        model.addAttribute("person", peopleService.findPerson(id));
        model.addAttribute("books", peopleService.findPerson(id).getBooks());
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);

        return "redirect:/readers";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id,
                             Model model){
        model.addAttribute("person", peopleService.findPerson(id));

        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String patchPerson(@PathVariable("id") int id,
                              @ModelAttribute("person") @Valid Person person,
                              BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(id, person);

        return "redirect:/readers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/readers";
    }
}
