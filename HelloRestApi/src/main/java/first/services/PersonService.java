package first.services;

import first.controller.vo.PersonVO;
import first.domain.Person;
import first.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public Person add(Person person) {
        Person newPerson = personRepository.save(person);
        logger.info("Add new Person, personId={}", newPerson.getId());
        return newPerson;
    }

    @Transactional
    public PersonVO add(PersonVO personVO){
        Person newPerson = new Person(personVO.getSurName(), personVO.getName());
        newPerson = personRepository.save(newPerson);
        logger.info("Add new Person, personId={}", newPerson.getId());
        return PersonVO.valueOf(newPerson);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
        logger.info("Delete Person, personId={}", id);
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
        logger.info("Update Person, personId={}", person.getId());
    }
}
