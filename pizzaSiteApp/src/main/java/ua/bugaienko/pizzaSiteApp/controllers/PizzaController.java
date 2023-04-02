package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.models.Pizza;
import ua.bugaienko.pizzaSiteApp.services.PersonService;
import ua.bugaienko.pizzaSiteApp.services.PizzaService;
import ua.bugaienko.pizzaSiteApp.util.UserUtil;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    private final PizzaService pizzaService;
    private final PersonService personService;
    private final UserUtil userUtil;

    @Autowired
    public PizzaController(PizzaService pizzaService, PersonService personService, UserUtil userUtil) {
        this.pizzaService = pizzaService;
        this.personService = personService;
        this.userUtil = userUtil;
    }

    @GetMapping("/addToFav/{Id}")
    public String addPizzaToPersonFav(@PathVariable("Id") int pizzaId){
        Person person = userUtil.getActiveUser();
        if (person == null) {
            System.out.println("NULL!");
            return "auth/needLogin";
        }
        personService.addPizzaToFav(person, pizzaService.findById(pizzaId));
        return "redirect:/menu";
    }
}
