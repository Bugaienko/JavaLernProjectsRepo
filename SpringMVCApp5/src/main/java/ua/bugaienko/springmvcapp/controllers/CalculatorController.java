package ua.bugaienko.springmvcapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/calc")
public class CalculatorController {

    @GetMapping("/task")
    public String getTask(@RequestParam(value = "a", required = false) int a,
                          @RequestParam(value = "b", required = false) int b,
                          @RequestParam(value = "action", required = false) String action,
                          Model model) {
        String answer = "Input values are incorrect";
        if (action != null) {
            switch (action) {
                case "mult":
                    answer = "" + a + " * " + b + " = " + (a * b);
                    break;
                case "add":
                    answer = "" + a + " + " + b + " = " + (a + b);
                    break;
                case "sub":
                    answer = "" + a + " - " + b + " = " + (a - b);
                    break;
                case "div":
                    if (a != 0 && b != 0) {
                        answer = "" + a + " / " + b + " = " + (a / (double) b);
                    }
                    break;
                default:
            }
        }
        model.addAttribute("answer", answer);

        return "/calc/answer";
    }
}
