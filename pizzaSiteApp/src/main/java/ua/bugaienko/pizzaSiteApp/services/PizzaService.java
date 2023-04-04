package ua.bugaienko.pizzaSiteApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.pizzaSiteApp.models.*;
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

    public List<Pizza> findAll() {
        return pizzaRepository.findAll(Sort.by(Sort.Order.by("name")).ascending());
    }

    public List<Pizza> findByPizzaSize(String size) {
        return pizzaRepository.findDistinctPizzaByBase_SizeLikeIgnoreCase(size, Sort.by("name").ascending());
    }

    public Pizza findById(int id) {
        return pizzaRepository.findById(id).get();
    }

    public List<Cafe> findCafesByPizzaId(int pizzaId) {
        Pizza pizza;
        List<Cafe> cafes = new ArrayList<>();
        Optional<Pizza> pizzaOpt = pizzaRepository.findById(pizzaId);
        if (pizzaOpt.isPresent()) {
            pizza = pizzaOpt.get();
            cafes = pizza.getCafes();
        }
        return cafes;
    }

    public double getCalculatedPrice(Pizza pizza) {
        List<Ingredient> ingredients = pizza.getIngredients();
        Base base = pizza.getBase();
        double multiplier = 1;
        if (base.getSize().equalsIgnoreCase("medium")) {
            multiplier = 1.3;
        } else if (base.getSize().equalsIgnoreCase("large")) {
            multiplier = 1.6;
        }
        double calcPrice = base.getPrice();
        for (Ingredient ing : ingredients) {
            calcPrice += ing.getPrice() * multiplier;
        }
        return calcPrice;
    }

    @Transactional
    public void setNewPrice(Pizza pizza, double newPrice) {
        pizza.setPrice(newPrice);
        pizzaRepository.save(pizza);
    }

    @Transactional
    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Optional<Pizza> findByName(String name) {
        return pizzaRepository.findByName(name);
    }

    public List<Pizza> findByPerson(Person person) {
        return pizzaRepository.findByPersons(person);
    }
}
