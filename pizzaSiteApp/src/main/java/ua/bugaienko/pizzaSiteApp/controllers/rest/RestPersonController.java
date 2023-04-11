package ua.bugaienko.pizzaSiteApp.controllers.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ua.bugaienko.pizzaSiteApp.controllers.dto.PersonDTO;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.services.PersonService;
import ua.bugaienko.pizzaSiteApp.util.ErrorResponse;
import ua.bugaienko.pizzaSiteApp.util.NotFoundException;
import ua.bugaienko.pizzaSiteApp.util.PersonNotCreatedException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergii Bugaienko
 */

@RestController
@RequestMapping("/api/users")
public class RestPersonController {

    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    public RestPersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<PersonDTO> getPersons() {
        return personService.findAll().stream()
                .map(this::convertToDtoPerson).collect(Collectors.toList());

//        List<Person> persons = personService.findAll();
//        List<PersonDTO> resultList = new ArrayList<>();
//        for (Person person : persons) {
//            resultList.add(convertToDtoPerson(person));
//        }
//        return resultList;
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int personId) {
        return convertToDtoPerson(personService.findOne(personId));
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDto, BindingResult bindingResult) {
        System.out.println("Pst start");
        if (bindingResult.hasErrors()) {
            //TODO
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        Person person = convertToPerson(personDto);
        personService.register(person);
        //отправляем ответ с пустым телом и статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "Object with this id wasn't found", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleCreatedException(PersonNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Person convertToPerson(PersonDTO personDto) {
//        ModelMapper mapper = new ModelMapper(); // Create Bean in config

        return modelMapper.map(personDto, Person.class);

//        return new Person(personDto.getUsername(), personDto.getPassword(), personDto.getEmail(), personDto.getAvatar());
    }

    private PersonDTO convertToDtoPerson(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }


}
