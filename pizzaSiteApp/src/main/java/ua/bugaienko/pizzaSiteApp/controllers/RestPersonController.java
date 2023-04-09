package ua.bugaienko.pizzaSiteApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.services.PersonService;
import ua.bugaienko.pizzaSiteApp.util.PersonErrorResponse;
import ua.bugaienko.pizzaSiteApp.util.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergii Bugaienko
 */

@RestController
@RequestMapping("/api/users")
public class RestPersonController {
    private final PersonService personService;

    @Autowired
    public RestPersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Person> getAll() {
        return personService.getAll();
    }
    @GetMapping("/{id}")
    public Person getUser(@PathVariable("id") int personId) {
        Person person = personService.getById(personId);
        System.out.println(person);
        return person;
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasn't found", System.currentTimeMillis()
        );


        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
