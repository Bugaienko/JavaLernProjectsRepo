package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.util.UserUtil;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserUtil userUtil;

    @Autowired
    public AdminController(UserUtil userUtil) {
        this.userUtil = userUtil;
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
}
