package ua.bugaienko.rest.FirstRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.bugaienko.rest.FirstRestApp.domain.Person;
import ua.bugaienko.rest.FirstRestApp.services.PersonService;
import ua.bugaienko.rest.FirstRestApp.util.PersonErrorResponse;
import ua.bugaienko.rest.FirstRestApp.util.PersonNotFoundException;

import java.util.List;

/**
 * @author Sergii Bugaienko
 */

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public List<Person> getPeople(){
        return personService.findAll(); // Jackson конвертирует в JSON
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int personId){
        return personService.getById(personId); //Jackson convert to JSON
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasn't found!", System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
