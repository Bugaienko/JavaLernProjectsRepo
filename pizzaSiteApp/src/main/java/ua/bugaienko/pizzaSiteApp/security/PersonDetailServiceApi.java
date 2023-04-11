package ua.bugaienko.pizzaSiteApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.repositiries.PersonDetailRepository;

/**
 * @author Sergii Bugaienko
 */

@Service
@Transactional(readOnly = true)
public class PersonDetailServiceApi implements UserDetailsService {

    private final PersonDetailRepository personDetailRepository;

    @Autowired
    public PersonDetailServiceApi(PersonDetailRepository personDetailRepository) {
        this.personDetailRepository = personDetailRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personDetailRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found + " + username));

        return new PersonDetails(person);

    }
}
