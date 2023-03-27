package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping()
    public String mainPage(){
        return "index";
    }
}
