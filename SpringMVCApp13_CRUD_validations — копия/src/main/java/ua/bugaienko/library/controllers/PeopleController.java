package ua.bugaienko.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.bugaienko.library.dao.PersonDAO;
import ua.bugaienko.library.models.Person_old;
import ua.bugaienko.library.utils.PersonValidator;

import javax.validation.Valid;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
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
    public String newPerson(@ModelAttribute("person") Person_old person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person_old person, BindingResult bindingResult) {

        //Spring Validator
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person_old person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            //TODO id in url?
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
