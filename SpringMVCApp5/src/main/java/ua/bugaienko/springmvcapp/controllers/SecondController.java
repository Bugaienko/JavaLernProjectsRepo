package ua.bugaienko.springmvcapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sergii Bugaienko
 */

@Controller
public class SecondController {
    @GetMapping("/exit")
    public String exit(){
        return "second/exit";
    }
}
