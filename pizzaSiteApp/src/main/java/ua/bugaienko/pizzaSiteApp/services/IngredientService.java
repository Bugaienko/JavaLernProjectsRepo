package ua.bugaienko.pizzaSiteApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.pizzaSiteApp.models.Ingredient;
import ua.bugaienko.pizzaSiteApp.models.Pizza;
import ua.bugaienko.pizzaSiteApp.models.TypeIngredient;
import ua.bugaienko.pizzaSiteApp.repositiries.IngredientRepository;
import ua.bugaienko.pizzaSiteApp.repositiries.TypesRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergii Bugaienko
 */

@Service
@Transactional(readOnly = true)
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final TypesRepository typesRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, TypesRepository typesRepository) {
        this.ingredientRepository = ingredientRepository;
        this.typesRepository = typesRepository;
    }

    public List<Ingredient> findAll(){
        return ingredientRepository.findAll();
    }

    public List<Ingredient> findByPizza(Pizza pizza){
        return ingredientRepository.findByPizzas(pizza, Sort.by("type"));
    }

    public List<Ingredient> findAllSort(){
        return ingredientRepository.findAll(Sort.by("type"));
    }


    public Optional<Ingredient> findIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }

    @Transactional
    public void create(Ingredient ingredient, TypeIngredient typeIngredient) {
       TypeIngredient type = typesRepository.getById(ingredient.getId());
        System.out.println(type);
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName(ingredient.getName());
        newIngredient.setPrice(ingredient.getPrice());
        newIngredient.setImage(ingredient.getImage());
        newIngredient.setType(type);
        System.out.println(newIngredient);
        ingredientRepository.save(newIngredient);
    }
}
