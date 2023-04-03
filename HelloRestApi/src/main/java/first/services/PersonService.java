package first.services;

import first.domain.Person;
import first.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        person.setSurName(personDataForUpdate.getSurName());
        personRepository.save(person);
    }
}
