package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.models.Pizza;
import ua.bugaienko.pizzaSiteApp.services.PersonService;
import ua.bugaienko.pizzaSiteApp.services.PizzaService;
import ua.bugaienko.pizzaSiteApp.util.PersonValidator;
import ua.bugaienko.pizzaSiteApp.util.UserUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/user") public class PersonController {

    private final UserUtil userUtil;
    private final PizzaService pizzaService;
    private final PersonValidator personValidator;
    private final PersonService personService;

    @Autowired
    public PersonController(UserUtil userUtil, PizzaService pizzaService, PersonValidator personValidator, PersonService personService) {
        this.userUtil = userUtil;
        this.pizzaService = pizzaService;
        this.personValidator = personValidator;
        this.personService = personService;
    }

    @GetMapping("/myPage")
    public String userPage(Model model){
        Person user = userUtil.getActiveUser();
        List<Pizza> pizzas = pizzaService.findByPerson(user);
        user.setFavorites(pizzas);

        model.addAttribute("user", user);
        model.addAttribute("pizzas", user.getSortedPizza());
        return "person/user";

    }

    @GetMapping("/edit")
    public String changeUserInfo(@ModelAttribute("person") Person person, Model model){
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        model.addAttribute("person", user);
        return "auth/edit";
    }

    @PatchMapping("/edit")
    public String updateUserInfo(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, String rePassword, Model model){
        Person user = userUtil.getActiveUser();
        personValidator.validate(person, user.getId(), bindingResult);
        personValidator.validate(person, rePassword, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/edit";
        }
        model.addAttribute("user", user);
        System.out.println("person upd -> " + person);
        personService.update(user, person);

        return "redirect:/user/myPage";

    }

}
