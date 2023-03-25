package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sergii Bugaienko
 */
@Controller
public class SimpleController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "boys");
        return "hello";
    }
}
