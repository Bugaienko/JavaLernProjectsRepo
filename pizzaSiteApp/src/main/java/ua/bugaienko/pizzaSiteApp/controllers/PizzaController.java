package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.bugaienko.pizzaSiteApp.models.Ingredient;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.models.Pizza;
import ua.bugaienko.pizzaSiteApp.models.Price;
import ua.bugaienko.pizzaSiteApp.services.IngredientService;
import ua.bugaienko.pizzaSiteApp.services.PersonService;
import ua.bugaienko.pizzaSiteApp.services.PizzaService;
import ua.bugaienko.pizzaSiteApp.util.UserUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    private final PizzaService pizzaService;
    private final PersonService personService;
    private final UserUtil userUtil;
    private final IngredientService ingredientService;

    @Autowired
    public PizzaController(PizzaService pizzaService, PersonService personService, UserUtil userUtil, IngredientService ingredientService) {
        this.pizzaService = pizzaService;
        this.personService = personService;
        this.userUtil = userUtil;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/addToFav/{Id}")
    public String addPizzaToPersonFav(@PathVariable("Id") int pizzaId){
        Person person = userUtil.getActiveUser();
        if (person == null) {
            return "auth/needLogin";
        }
        personService.addPizzaToFav(person, pizzaService.findById(pizzaId));
        return "redirect:/menu";
    }

    @GetMapping("/{id}")
    public String pizzaPage(@PathVariable("id") int pizzaId, Model model){
        model.addAttribute("user", userUtil.getActiveUser());
        Pizza pizza = pizzaService.findById(pizzaId);
        model.addAttribute("pizza",  pizza);
        List<Ingredient> ing = ingredientService.findByPizza(pizza);


        model.addAttribute("ingredients", ing);
        return "pizza/detail";
    }

    @GetMapping("/checkPrice/{id}")
    public String checkPricePage(@PathVariable("id") int pizzaId, Model model, @ModelAttribute("price") Price price){
        model.addAttribute("user", userUtil.getActiveUser());

        Pizza pizza = pizzaService.findById(pizzaId);
        model.addAttribute("pizza", pizza);

        List<Ingredient> ing = ingredientService.findByPizza(pizza);
        model.addAttribute("ingredients", ing);

        double calcPrice = pizzaService.getCalculatedPrice(pizza);
        model.addAttribute("recPrice", calcPrice);

        return "pizza/checkPrice";
    }

    @PostMapping("/setPrice/{id}")
    public String setNewPrice(@PathVariable("id") int pizzaId, @ModelAttribute("price") @Valid Price price){
        Pizza pizza = pizzaService.findById(pizzaId);
        double newPrice = price.getPrice();
        pizzaService.setNewPrice(pizza, newPrice);
        return "redirect:/menu";
    }
}
