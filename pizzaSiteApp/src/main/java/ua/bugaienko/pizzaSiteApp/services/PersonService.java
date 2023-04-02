package ua.bugaienko.pizzaSiteApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.models.Pizza;
import ua.bugaienko.pizzaSiteApp.repositiries.PersonRepository;
import ua.bugaienko.pizzaSiteApp.repositiries.PizzaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Sergii Bugaienko
 */

@Service
@Transactional(readOnly = true)
public class PersonService {

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
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }

    @Transactional
    public void addPizzaToFav(Person person, Pizza pizza){
//        List<Pizza> favorites = person.getFavorites();
        List<Pizza> favorites = pizzaRepository.findByPersons(person);

        System.out.println("favorites: " + favorites);
        if (favorites == null){
            System.out.println("Null fav");
            favorites = new ArrayList<>();
        }
        favorites.add(pizza);
        person.setFavorites(Person.deleteDublicates(favorites));
        personRepository.save(person);
    }
}
