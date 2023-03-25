package spring.services;

import org.springframework.stereotype.Service;
import spring.domain.Person;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergii Bugaienko
 */

@Service
public class PersonService {
    private List<Person> persons;

    @PostConstruct
    public void doMyInit() {
        persons = new ArrayList<>();
        persons.add(new Person("Ivanov", "Ivan"));
        persons.add(new Person("Petrov", "Petr"));
    }

    public List<Person> findAll(){
        return persons;
    }

    public void add(Person person) {
        person.setId();
        persons.add(person);
    }

    public void delete(int id) {
        persons.removeIf(p -> p.getId() == id);
    }

    public Person getById(int id) {
        return persons.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void update(Person personDataForUpdate, int id) {
        Person person = getById(id);
        person.setName(personDataForUpdate.getName());
        person.setSurname(personDataForUpdate.getSurname());
    }
}
