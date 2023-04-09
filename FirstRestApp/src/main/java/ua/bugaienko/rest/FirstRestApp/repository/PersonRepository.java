package ua.bugaienko.rest.FirstRestApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.bugaienko.rest.FirstRestApp.domain.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
