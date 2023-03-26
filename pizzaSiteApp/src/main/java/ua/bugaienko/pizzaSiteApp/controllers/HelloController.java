package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.bugaienko.pizzaSiteApp.services.IngredientService;

/**
 * @author Sergii Bugaienko
 */

@Controller
public class HelloController {

    private final IngredientService ingredientService;

    @Autowired
    public HelloController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/test")
    public String testPage(Model model){
        model.addAttribute("ingredients", ingredientService.findAll());
        return "test";
    }
}
