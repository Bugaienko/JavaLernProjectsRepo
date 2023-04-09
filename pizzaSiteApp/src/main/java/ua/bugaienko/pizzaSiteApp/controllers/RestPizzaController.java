package ua.bugaienko.pizzaSiteApp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.bugaienko.pizzaSiteApp.models.Pizza;
import ua.bugaienko.pizzaSiteApp.services.PizzaService;
import ua.bugaienko.pizzaSiteApp.util.responses.ErrorResponse;
import ua.bugaienko.pizzaSiteApp.util.responses.NotFoundException;

/**
 * @author Sergii Bugaienko
 */

@RestController
@RequestMapping("/api/pizza")
public class RestPizzaController {

    private final PizzaService pizzaService;
    private final Logger logger = LoggerFactory.getLogger(RestPizzaController.class);

    @Autowired
    public RestPizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable("id") int pizzaId){
        Pizza pizza = pizzaService.findById(pizzaId);
        System.out.println(pizza);
        return pizza;
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(NotFoundException e){
        ErrorResponse response = new ErrorResponse(
                "Pizza with this id wasn't found!", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
