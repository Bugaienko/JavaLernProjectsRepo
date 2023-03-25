package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.domain.Person;
import spring.services.PersonService;

/**
 * @author Sergii Bugaienko
 */
@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("persons", personService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("person") Person person) {
        return "add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("person") Person person) {
        personService.add(person);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        personService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getById(id));
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {
        personService.update(person, id);
        return "redirect:/";
    }


}
