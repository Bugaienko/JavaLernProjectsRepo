package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.services.PersonService;
import ua.bugaienko.pizzaSiteApp.util.PersonValidator;
import ua.bugaienko.pizzaSiteApp.util.UserUtil;

import javax.validation.Valid;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final PersonService personService;
    private final UserUtil userUtil;

    @Autowired
    public AuthController(PersonValidator personValidator, PersonService personService, UserUtil userUtil) {
        this.personValidator = personValidator;
        this.personService = personService;
        this.userUtil = userUtil;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person")Person person){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        personService.register(person);

        return "redirect:/auth/login";
    }

    @GetMapping("/exit")
    public String confirmLogout(Model model){
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        return "auth/exit";
    }

}
