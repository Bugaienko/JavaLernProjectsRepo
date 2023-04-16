package pizzaRest.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pizzaRest.models.Cafe;

@Service
public interface CafeRepository extends JpaRepository<Cafe, Integer> {

}
