package ua.bugaienko.FirstSecurityApp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.bugaienko.FirstSecurityApp.security.PersonDetails;

/**
 * @author Sergii Bugaienko
 */

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/showInfo")
    public String showUserDetail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());

        return "hello";
    }
}
