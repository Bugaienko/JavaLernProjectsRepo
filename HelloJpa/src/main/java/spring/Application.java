package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.AppConfig;
import spring.domain.Person;
import spring.service.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Sergii Bugaienko
 */

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.scan("spring");

        PersonService personService = (PersonService) context.getBean("personService", PersonService.class);

        Person person = new Person("Sergey", "Sergeev");
        personService.add(person);

        Iterable<Person> persons = personService.findAll();
        List<Person> personList = new ArrayList<>();
        persons.forEach(personList::add);
        System.out.println(personList);

    }
}
