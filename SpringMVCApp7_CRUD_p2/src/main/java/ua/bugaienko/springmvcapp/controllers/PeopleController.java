package ua.bugaienko.springmvcapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.bugaienko.springmvcapp.dao.PersonDAO;
import ua.bugaienko.springmvcapp.models.Person;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model) {
        // Получим всех людей из DAO и передадим на отображение в представлегнии
        model.addAttribute("people", personDAO.getAll());
        return "people/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //Получим одного человека по id из DAO и передадим его на представление

        model.addAttribute("person", personDAO.getById(id));
        return "people/show";
    }

//    @GetMapping("/new")
//    public String newPerson(Model model) {
//        model.addAttribute("person", new Person());
//        return "people/new";
//    }
    // 2nd realization newPerson
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }
}
