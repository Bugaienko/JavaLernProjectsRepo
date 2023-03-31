package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.bugaienko.pizzaSiteApp.models.Pizza;
import ua.bugaienko.pizzaSiteApp.services.PizzaService;
import ua.bugaienko.pizzaSiteApp.util.UserUtil;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/cafe")
public class CafesController {

    private final PizzaService pizzaService;
    private final UserUtil userUtil;

    @Autowired
    public CafesController(PizzaService pizzaService, UserUtil userUtil) {
        this.pizzaService = pizzaService;
        this.userUtil = userUtil;
    }

    @GetMapping("/pizza/{pizzaId}")
    public String pizzaAvailability(@PathVariable("pizzaId") int pizzaId, Model model) {
        model.addAttribute("user", userUtil.getActiveUser());
        Pizza pizza = pizzaService.findById(pizzaId);
        model.addAttribute("pizza", pizza);
        return "cafe/pizzaSearch";
    }
}
