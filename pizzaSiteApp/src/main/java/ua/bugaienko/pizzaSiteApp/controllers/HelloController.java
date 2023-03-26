package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sergii Bugaienko
 */

@Controller
public class HelloController {
    @GetMapping("/test")
    public String testPage(){
        return "test";
    }
}
