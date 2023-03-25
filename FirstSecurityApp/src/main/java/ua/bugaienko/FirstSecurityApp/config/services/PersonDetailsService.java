package ua.bugaienko.FirstSecurityApp.config.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.FirstSecurityApp.models.Person;
import ua.bugaienko.FirstSecurityApp.repositories.PersonRepository;
import ua.bugaienko.FirstSecurityApp.security.PersonDetails;

import java.util.Optional;

/**
 * @author Sergii Bugaienko
 */

@Service
@Transactional(readOnly = true)
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByUserName(username);

        if (!person.isPresent()) {
            throw  new UsernameNotFoundException("User not found!");
        }

        return new PersonDetails(person.get());
    }
}
