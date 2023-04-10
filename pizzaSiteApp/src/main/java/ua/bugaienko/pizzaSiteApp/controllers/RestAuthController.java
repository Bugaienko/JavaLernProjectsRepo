package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.services.PersonService;

import java.util.Objects;

/**
 * @author Sergii Bugaienko
 */

@RestController
@RequestMapping("/api")
public class RestAuthController {
    private final PersonService personService;

    @Autowired
    public RestAuthController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Person getAuthUser() {
        System.out.println("Auth rest -> Attempt auth");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        User user = (principal instanceof Person) ? (User) principal : null;
        return Objects.nonNull(user) ? personService.getByName(user.getUsername()) : null;
    }
}
