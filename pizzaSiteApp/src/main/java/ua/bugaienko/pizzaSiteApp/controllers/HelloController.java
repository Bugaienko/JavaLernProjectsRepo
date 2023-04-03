package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.bugaienko.pizzaSiteApp.security.PersonDetails;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
        model.addAttribute("ingredients", ingredientService.findAll());
        return "test";
    }

    @GetMapping("/ddd")
    public String debugPage() {
        return "temp/index";
    }
}
