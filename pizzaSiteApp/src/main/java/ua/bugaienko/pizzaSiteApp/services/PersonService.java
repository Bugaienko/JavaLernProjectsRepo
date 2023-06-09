package ua.bugaienko.pizzaSiteApp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.models.Pizza;
import ua.bugaienko.pizzaSiteApp.repositiries.PersonRepository;
import ua.bugaienko.pizzaSiteApp.repositiries.PizzaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergii Bugaienko
 */

@Service
@Transactional(readOnly = true)
public class PersonService {
    static final Logger logger = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;
    private final PizzaRepository pizzaRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PizzaRepository pizzaRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.pizzaRepository = pizzaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Person> findUserByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    public Optional<Person> findUserByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Transactional
    public Person register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        logger.info("Add new Person, personId={}", person.getId());
        return personRepository.save(person);
    }

    @Transactional
    public void addPizzaToFav(Person person, Pizza pizza) {
//        List<Pizza> favorites2 = person.getFavorites();
        List<Pizza> favorites = pizzaRepository.findByPersons(person);

        if (!favorites.contains(pizza)) {
            favorites.add(pizza);
        }
        person.setFavorites(favorites);
        personRepository.save(person);
        logger.info("Add Fav pizza{} to Person, personId={}",pizza.getId(), person.getId());
    }

    @Transactional
    public void removePizzaFromFav(Person person, Pizza pizza) {
        List<Pizza> favorites = pizzaRepository.findByPersons(person);
        favorites.remove(pizza);
        person.setFavorites(favorites);

        personRepository.save(person);
        logger.info("Del pizza{} from Person id={} Fav", pizza.getId(), person.getId());
    }
}
