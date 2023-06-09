package ua.bugaienko.springmvcapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.bugaienko.springmvcapp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Sergii Bugaienko
 */

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getById(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM person WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)
        ).stream().findAny().orElse(null);
    }

    public Optional<Person> getByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM person WHERE email=?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person) {
        System.out.println("Save Person");

        jdbcTemplate.update("INSERT INTO person(name, age, email) VALUES (?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?",
                id);

    }
}
