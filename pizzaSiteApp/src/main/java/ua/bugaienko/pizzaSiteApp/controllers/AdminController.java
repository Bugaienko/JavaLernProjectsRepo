package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.bugaienko.pizzaSiteApp.models.*;
import ua.bugaienko.pizzaSiteApp.services.*;
import ua.bugaienko.pizzaSiteApp.util.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserUtil userUtil;
    private final TypeService typeService;
    private final IngredientService ingredientService;
    private final BaseService baseService;
    private final PizzaService pizzaService;
    private final CafeService cafeService;
    private final IngredientValidator ingredientValidator;
    private final TypeValidator typeValidator;
    private final BaseValidator baseValidator;
    private final PizzaValidator pizzaValidator;

    @Autowired
    public AdminController(UserUtil userUtil, TypeService typeService, IngredientService ingredientService, BaseService baseService, PizzaService pizzaService, CafeService cafeService, IngredientValidator ingredientValidator, TypeValidator typeValidator, BaseValidator baseValidator, PizzaValidator pizzaValidator) {
        this.userUtil = userUtil;
        this.typeService = typeService;
        this.ingredientService = ingredientService;
        this.baseService = baseService;
        this.pizzaService = pizzaService;
        this.cafeService = cafeService;
        this.ingredientValidator = ingredientValidator;
        this.typeValidator = typeValidator;
        this.baseValidator = baseValidator;
        this.pizzaValidator = pizzaValidator;
    }

    @GetMapping()
    public String indexAdmin(Model model) {
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        if (!user.getRole().toLowerCase().contains("admin")) {
            return "admin/accessDenied";
        }

        return "admin/index";
    }

    @GetMapping("/add/ingredient")
    public String addIngredient(Model model,
                                @ModelAttribute("ingredient") Ingredient ingredient,
                                @ModelAttribute("type") TypeIngredient typeIngredient) {
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        if (!user.getRole().toLowerCase().contains("admin")) {
            return "admin/accessDenied";
        }
        List<TypeIngredient> types = typeService.findAllSorted();
        model.addAttribute("types", types);
        return "admin/addIngredient";
    }

    @PostMapping("/add/ingredient")
    public String createIngredient(@ModelAttribute("ingredient") @Valid Ingredient ingredient, BindingResult bindingResult, @ModelAttribute("type") TypeIngredient typeIngredient, Model model) {
        ingredientValidator.validate(ingredient, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userUtil.getActiveUser());
            return "admin/addIngredient";
        }

        ingredientService.create(ingredient, typeIngredient);
        model.addAttribute("user", userUtil.getActiveUser());

        return "redirect:/admin";
    }

    @GetMapping("/add/type_ingredient")
    public String addTypeIngredient(@ModelAttribute("type") TypeIngredient type, Model model) {
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        if (!user.getRole().toLowerCase().contains("admin")) {
            return "admin/accessDenied";
        }
        return "admin/addTypeIngredient";
    }

    @PostMapping("/add/type_ingredient")
    public String createType(@ModelAttribute("type") @Valid TypeIngredient type, BindingResult bindingResult, Model model) {
        typeValidator.validate(type, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userUtil.getActiveUser());
            return "admin/addTypeIngredient";
        }
        typeService.create(type);
        return "redirect:/admin";
    }

    @GetMapping("/add/base")
    public String addBase(@ModelAttribute("base") Base base, Model model) {
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        if (!user.getRole().toLowerCase().contains("admin")) {
            return "admin/accessDenied";
        }
        return "admin/addBase";
    }

    @PostMapping("add/base")
    public String createBase(@ModelAttribute("base") @Valid Base base, BindingResult bindingResult, Model model) {
        model.addAttribute("user", userUtil.getActiveUser());
        baseValidator.validate(base, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/addBase";
        }
        baseService.create(base);
        return "redirect:/admin";
    }

    @GetMapping("base/edit")
    public String choiceBase(@ModelAttribute("base") Base base, Model model) {
        model.addAttribute("user", userUtil.getActiveUser());

        List<Base> bases = baseService.findAllSorted();
        model.addAttribute("bases", bases);
        return "admin/choiceBase";
    }

    @PostMapping("base/edit")
    public String editBase(@ModelAttribute("base") Base base, Model model) {
        model.addAttribute("user", userUtil.getActiveUser());
        Base base1 = baseService.findById(base.getId());
        model.addAttribute("base", base1);
        return "admin/editBase";
    }

    @PatchMapping("base/edit/{id}")
    public String updateBase(@ModelAttribute("base") @Valid Base base, BindingResult bindingResult,
                             @PathVariable("id") int baseId, Model model) {

        model.addAttribute("user", userUtil.getActiveUser());

        if (bindingResult.hasErrors()) {
            return "admin/editBase";
        }

        baseService.update(base);
        return "redirect:/admin";

    }

    @GetMapping("/add/pizza")
    public String addPizza(@ModelAttribute("pizza") Pizza pizza,
                           @ModelAttribute("base") Base base,
                           @ModelAttribute("ingredient") Ingredient ingredient,
                           Model model) {
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        if (!user.getRole().toLowerCase().contains("admin")) {
            return "admin/accessDenied";
        }
        List<Ingredient> ingredients = ingredientService.findAllSort();
        List<Base> bases = baseService.findAllSorted();
        int ingCount = ingredients.size();
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("bases", bases);
        model.addAttribute("ingCount", ingCount);
        return "admin/addPizza";
    }

    @PostMapping("/add/pizza")
    public String creatPizza(@ModelAttribute("pizza") @Valid Pizza pizza, BindingResult bindingResult, Model model) {
        model.addAttribute("user", userUtil.getActiveUser());
        pizzaValidator.validate(pizza, bindingResult);

        if (bindingResult.hasErrors()) {
            List<Ingredient> ingredients = ingredientService.findAllSort();
            List<Base> bases = baseService.findAllSorted();
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("bases", bases);
            return "admin/addPizza";
        }
        System.out.println(pizza);
        System.out.println(pizza.getIngredients());
        Pizza newPizza = pizzaService.create(pizza);
        if (newPizza != null) {
            return "redirect:/pizza/" + newPizza.getId();
        }
        return "redirect:/admin";
    }

    @GetMapping("/add/cafe")
    public String addCafe(@ModelAttribute("cafe") Cafe cafe, Model model) {
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        if (!user.getRole().toLowerCase().contains("admin")) {
            return "admin/accessDenied";
        }
        return "admin/addCafe";
    }

    @PostMapping("/add/cafe")
    public String createCafe(@ModelAttribute("cafe") @Valid Cafe cafe, BindingResult bindingResult, Model model) {
        model.addAttribute("user", userUtil.getActiveUser());

        if (bindingResult.hasErrors()) {
            return "admin/addCafe";
        }

        Cafe newCafe = cafeService.create(cafe);
        System.out.println(newCafe);
        if (newCafe != null) {
            return "redirect:/cafe/" + newCafe.getId();
        }

        return "redirect:/admin";
    }

}
