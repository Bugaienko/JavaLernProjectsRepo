package ua.bugaienko.springBootFirst.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sergii Bugaienko
 */

@Controller
public class HelloController {

    private String hello = "fuck";

    @GetMapping("/hello")
    public String hello() {
        System.out.println(hello);
        return "hello";
    }
}
