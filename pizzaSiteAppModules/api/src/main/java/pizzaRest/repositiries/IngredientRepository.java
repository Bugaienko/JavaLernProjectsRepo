package pizzaRest.repositiries;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pizzaRest.models.Ingredient;
import pizzaRest.models.Pizza;


import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    List<Ingredient> findByPizzas(Pizza pizza, Sort type);

    Optional<Ingredient> findByName(String name);
}
