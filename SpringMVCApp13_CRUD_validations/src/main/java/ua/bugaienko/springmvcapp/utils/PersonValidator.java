package ua.bugaienko.springmvcapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.bugaienko.springmvcapp.dao.PersonDAO;
import ua.bugaienko.springmvcapp.models.Person;

/**
 * @author Sergii Bugaienko
 */

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.getByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This Email is already taken");
        }
        //Есть ли такой человек в БД?


    }
}
