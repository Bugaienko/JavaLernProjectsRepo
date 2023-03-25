package ua.bugaienko.library.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.bugaienko.library.dao.PersonDAO;
import ua.bugaienko.library.models.Person_old;

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
        return Person_old.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person_old person = (Person_old) target;

        if (personDAO.getByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This Email is already taken");
        }
        //Есть ли такой человек в БД?


    }
}
