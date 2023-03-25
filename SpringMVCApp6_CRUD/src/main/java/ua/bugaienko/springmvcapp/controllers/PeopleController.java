package ua.bugaienko.springmvcapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.bugaienko.springmvcapp.dao.PersonDAO;

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


    @GetMapping("/")
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
}
