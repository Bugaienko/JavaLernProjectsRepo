package ua.bugaienko.springBootFirst.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sergii Bugaienko
 */

@Controller
public class HelloController {

    @GetMapping("/hello")
    public  String hello() {
        return "hello";
    }
}
