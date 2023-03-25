package ua.bugaienko.springmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sergii Bugaienko
 */

@Controller
public class HelloController {
    @GetMapping("/hello-world")
    public String sayHello(){
        return "hello_world";
    }

}
