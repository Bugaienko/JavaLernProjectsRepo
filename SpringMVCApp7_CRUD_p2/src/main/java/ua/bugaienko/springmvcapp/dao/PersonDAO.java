package ua.bugaienko.springmvcapp.dao;

import org.springframework.stereotype.Component;
import ua.bugaienko.springmvcapp.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergii Bugaienko
 */

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;
    private List<Person> people;

    public PersonDAO() {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Anna"));
        people.add(new Person(++PEOPLE_COUNT, "Bob"));
        people.add(new Person(++PEOPLE_COUNT, "Cecil"));
        people.add(new Person(++PEOPLE_COUNT, "Danil"));
    }


    public List<Person> getAll() {
        return people;
    }

    public Person getById(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        System.out.println("Save Person");
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

}
