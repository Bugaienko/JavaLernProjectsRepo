package ua.bugaienko.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.bugaienko.library.dao.LibraryDAO;

/**
 * @author Sergii Bugaienko
 */

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final LibraryDAO libraryDAO;

    @Autowired
    public ClientController(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }

    @GetMapping
    public String clients(Model model){
        model.addAttribute("clients", libraryDAO.getAll());
        return "client/index";
    }
}
