package pizzaRest.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pizzaRest.models.Person;
import pizzaRest.security.PersonDetails;

/**
 * @author Sergii Bugaienko
 */

@Component
public class AuthUtil {
    public Person getActive(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) (authentication.getPrincipal());
        Person person = personDetails.getPerson();
        System.out.println("Auth util -> person -> " + person);
        return person;


    }
}
