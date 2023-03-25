package ua.bugaienko.library.dao;

import org.springframework.jdbc.core.RowMapper;
import ua.bugaienko.library.models.Person_old;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sergii Bugaienko
 */

public class PersonMapper implements RowMapper<Person_old> {
    @Override
    public Person_old mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Person_old person = new Person_old();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));

        return person;

    }
}
