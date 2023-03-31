package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.security.PersonDetails;
import ua.bugaienko.pizzaSiteApp.services.CafeService;
import ua.bugaienko.pizzaSiteApp.util.UserUtil;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/")
public class MainController {

    private final CafeService cafeService;
    private final UserUtil userUtil;


    @Autowired
    public MainController(CafeService cafeService, UserUtil userUtil) {
        this.cafeService = cafeService;
        this.userUtil = userUtil;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        model.addAttribute("cafe", cafeService.findById(1));
        return "main/index";
    }

    @GetMapping("/menu")
    public String menuPage(Model model) {
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        return "main/menu";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        return "main/about";
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        Person user = userUtil.getActiveUser();
        model.addAttribute("user", user);
        return "main/contact";
    }


}
