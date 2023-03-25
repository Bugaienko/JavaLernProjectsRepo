package ua.bugaienko.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.bugaienko.FirstSecurityApp.models.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUserName(String username);
}
