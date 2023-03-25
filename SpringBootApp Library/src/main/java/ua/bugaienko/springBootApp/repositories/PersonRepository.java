package ua.bugaienko.springBootApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.bugaienko.springBootApp.models.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByFullName(String fullName);
}
