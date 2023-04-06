package first;

import first.domain.Person;
import first.services.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Sergii Bugaienko
 */


@SpringBootTest
public class PersonServiceTest {

    private final PersonService personService;

    static final String name = "Jan";
    static final String surName = "Janov";

    @Autowired
    public PersonServiceTest(PersonService personService) {
        this.personService = personService;
    }

    @Test
    public void testAdd() {
        List<Person> people = personService.findAll();
        int count1 = people.size();


        Person person1 = new Person(surName, name);
        Person addedPerson = personService.add(person1);

        people = personService.findAll();
        int count2 = people.size();
        Assertions.assertEquals(count1 + 1, count2);
        Assertions.assertEquals(name, addedPerson.getName());
        Assertions.assertEquals(surName, addedPerson.getSurName());
    }
}
