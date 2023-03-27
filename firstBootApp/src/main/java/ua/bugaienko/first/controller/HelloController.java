package ua.bugaienko.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/")
public class HelloController {

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }
}
