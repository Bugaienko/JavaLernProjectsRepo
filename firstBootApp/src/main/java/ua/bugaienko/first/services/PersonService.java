package ua.bugaienko.first.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.first.domain.Person;
import ua.bugaienko.first.repository.PersonRepository;

import java.util.List;

/**
 * @author Sergii Bugaienko
 */

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public void add(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public Person getById(int id) {
        return personRepository.findById(id).get();
    }

    @Transactional
    public void update(Person personDataForUpdate, int id) {
        Person person = getById(id);
        person.setName(personDataForUpdate.getName());
        person.setSurname(personDataForUpdate.getSurname());
        personRepository.save(person);
    }
}
