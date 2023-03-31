package ua.bugaienko.pizzaSiteApp.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.bugaienko.pizzaSiteApp.models.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
