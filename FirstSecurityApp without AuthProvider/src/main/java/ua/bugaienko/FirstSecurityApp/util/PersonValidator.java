package ua.bugaienko.FirstSecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.bugaienko.FirstSecurityApp.models.Person;
import ua.bugaienko.FirstSecurityApp.services.PersonService;

import java.util.Optional;

/**
 * @author Sergii Bugaienko
 */

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person targetPerson = (Person) target;

        Optional<Person> result = personService.findUserByUserName(targetPerson.getUserName());

        if (result.isPresent()) {
        //     Username already busy
            errors.rejectValue("userName", "", "This username is already in use");
        }


    }
}
