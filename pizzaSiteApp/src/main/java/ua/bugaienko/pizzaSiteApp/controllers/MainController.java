package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.bugaienko.pizzaSiteApp.services.CafeService;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/")
public class MainController {

    private final CafeService cafeService;

    @Autowired
    public MainController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("cafe", cafeService.findById(1));
        return "main/index";
    }

    @GetMapping("/menu")
    public String menuPage(Model model) {
        return "main/menu";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "main/about";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "main/contact";
    }


}
