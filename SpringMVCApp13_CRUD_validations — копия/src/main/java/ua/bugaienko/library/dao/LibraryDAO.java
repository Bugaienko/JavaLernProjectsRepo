package ua.bugaienko.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.bugaienko.library.models.Person;

import java.util.List;

/**
 * @author Sergii Bugaienko
 */

@Component
public class LibraryDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LibraryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAll(){
        return jdbcTemplate.query("SELECT * FROM client", new BeanPropertyRowMapper<>(Person.class));
    }
}
