package ua.bugaienko.springmvcapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.bugaienko.springmvcapp.models.Person_old;

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


    public List<Person_old> getAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person_old.class));
    }

    public Person_old getById(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM person WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person_old.class)
        ).stream().findAny().orElse(null);
    }

    public Optional<Person_old> getByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM person WHERE email=?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(Person_old.class)).stream().findAny();
    }

    public void save(Person_old person) {
        System.out.println("Save Person");

        jdbcTemplate.update("INSERT INTO person(name, age, email, address) VALUES (?, ?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail(), person.getAddress());
    }

    public void    update(int id, Person_old updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=?, address=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), updatedPerson.getAddress(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?",
                id);

    }
}
