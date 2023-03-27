package ua.bugaienko.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.bugaienko.first.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
