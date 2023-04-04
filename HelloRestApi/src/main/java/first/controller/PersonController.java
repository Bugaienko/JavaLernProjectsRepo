package first.controller;


import first.domain.Person;
import first.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



/**
 * @author Sergii Bugaienko
 */
@Controller
public class PersonController {


    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

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
