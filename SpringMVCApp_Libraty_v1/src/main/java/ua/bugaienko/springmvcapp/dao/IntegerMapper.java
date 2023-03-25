package ua.bugaienko.springmvcapp.dao;

import org.springframework.jdbc.core.RowMapper;
import ua.bugaienko.springmvcapp.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sergii Bugaienko
 */

public class IntegerMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(
                rs.getInt("person_id"),
                rs.getString("full_name"),
                rs.getInt("year_birthday"));
    }
}
