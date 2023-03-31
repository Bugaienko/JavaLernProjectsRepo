package ua.bugaienko.pizzaSiteApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.pizzaSiteApp.models.Cafe;
import ua.bugaienko.pizzaSiteApp.models.Pizza;
import ua.bugaienko.pizzaSiteApp.repositiries.PizzaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Sergii Bugaienko
 */

@Service
@Transactional(readOnly = true)
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> findAll(){
        return pizzaRepository.findAll();
    }

    public Pizza findById(int id){
        return pizzaRepository.findById(id).get();
    }

    public List<Cafe> findCafesByPizzaId(int pizzaId){
        Pizza pizza;
        List<Cafe> cafes = new ArrayList<>();
        Optional<Pizza> pizzaOpt = pizzaRepository.findById(pizzaId);
        if (pizzaOpt.isPresent()){
            pizza = pizzaOpt.get();
            cafes = pizza.getCafes();
        }
        return cafes;
    }
}
