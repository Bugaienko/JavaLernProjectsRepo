package ua.bugaienko.pizzaSiteApp.repositiries;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.models.Pizza;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    public List<Pizza> findDistinctPizzaByBase_SizeContainingIgnoreCase(String size);

    public List<Pizza> findDistinctPizzaByBase_SizeLikeIgnoreCase(String size, Sort name);

    public List<Pizza> findByPersons(Person person);
}
