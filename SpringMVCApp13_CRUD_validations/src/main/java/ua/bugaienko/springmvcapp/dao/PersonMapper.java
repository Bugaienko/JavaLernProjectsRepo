package ua.bugaienko.springmvcapp.dao;

import org.springframework.jdbc.core.RowMapper;
import ua.bugaienko.springmvcapp.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sergii Bugaienko
 */

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));

        return person;

    }
}
