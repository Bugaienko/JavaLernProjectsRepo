package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.bugaienko.pizzaSiteApp.models.Base;
import ua.bugaienko.pizzaSiteApp.models.Ingredient;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.models.TypeIngredient;
import ua.bugaienko.pizzaSiteApp.services.BaseService;
import ua.bugaienko.pizzaSiteApp.services.IngredientService;
import ua.bugaienko.pizzaSiteApp.services.TypeService;
import ua.bugaienko.pizzaSiteApp.util.BaseValidator;
import ua.bugaienko.pizzaSiteApp.util.IngredientValidator;
import ua.bugaienko.pizzaSiteApp.util.TypeValidator;
import ua.bugaienko.pizzaSiteApp.util.UserUtil;

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
    private final IngredientValidator ingredientValidator;
    private final TypeValidator typeValidator;
    private final BaseValidator baseValidator;

    @Autowired
    public AdminController(UserUtil userUtil, TypeService typeService, IngredientService ingredientService, BaseService baseService, IngredientValidator ingredientValidator, TypeValidator typeValidator, BaseValidator baseValidator) {
        this.userUtil = userUtil;
        this.typeService = typeService;
        this.ingredientService = ingredientService;
        this.baseService = baseService;
        this.ingredientValidator = ingredientValidator;
        this.typeValidator = typeValidator;
        this.baseValidator = baseValidator;
    }

    @GetMapping()
    public String indexAdmin(Model model){
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        if (!user.getRole().toLowerCase().contains("admin")) {
            return "admin/accessDenied";
        }

        return "admin/index";
    }

    @GetMapping("/add/ingredient")
    public String addIngredient(Model model,
                                @ModelAttribute("ingredient")Ingredient ingredient,
                                @ModelAttribute("type") TypeIngredient typeIngredient){
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
            return "admin/addIngredient";
        }

        ingredientService.create(ingredient, typeIngredient);
        model.addAttribute("user", userUtil.getActiveUser());

        return "redirect:/admin";
    }

    @GetMapping("/add/type_ingredient")
    public String addTypeIngredient(@ModelAttribute("type") TypeIngredient type, Model model){
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        if (!user.getRole().toLowerCase().contains("admin")) {
            return "admin/accessDenied";
        }
        return "admin/addTypeIngredient";
    }

    @PostMapping ("/add/type_ingredient")
    public String createType(@ModelAttribute("type") @Valid TypeIngredient type, BindingResult bindingResult) {
        typeValidator.validate(type, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/addTypeIngredient";
        }
        typeService.create(type);
        return "redirect:/admin";
    }

    @GetMapping("/add/base")
    public  String addBase(@ModelAttribute("base")Base base, Model model){
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        if (!user.getRole().toLowerCase().contains("admin")) {
            return "admin/accessDenied";
        }
        return "admin/addBase";
    }

    @PostMapping("add/base")
    public String createBase(@ModelAttribute("base") @Valid Base base, BindingResult bindingResult){
        baseValidator.validate(base, bindingResult);
        if (bindingResult.hasErrors()){
            return "admin/addBase";
        }
        baseService.create(base);
        return "redirect:/admin";
    }
}
