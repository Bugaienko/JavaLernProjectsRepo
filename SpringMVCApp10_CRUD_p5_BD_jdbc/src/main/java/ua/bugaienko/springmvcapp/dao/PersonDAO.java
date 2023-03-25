package ua.bugaienko.springmvcapp.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ua.bugaienko.springmvcapp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergii Bugaienko
 */

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;

    private static String URL = "jdbc:postgresql://localhost:5432/baludb";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "пароль";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }


//    public PersonDAO() {
//        people = new ArrayList<>();
//        people.add(new Person(++PEOPLE_COUNT, "Anna", 24, "anna@gmail.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Bob", 48, "bob@gmail.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Cecil", 19, "cecil@gmail.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Danil", 35, "danil@gmail.com"));
//    }


    public List<Person> getAll() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person getById(int id) {
        Person person = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM person WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            //i have not unique id in db
            resultSet.next();
            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return person;
    }

    public void save(Person person) {
        System.out.println("Save Person");


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO person VALUES (1, ?, ?, ?)"
            );
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();

            // NOT SAFE. SQL injection.
//            Statement statement = connection.createStatement();
//            String query = "INSERT INTO Person VALUES(" + 1 + ", '" + person.getName() +
//                    "'," + person.getAge() + ",'" + person.getEmail() + "')";
//            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE person SET name=?, age=?, email=? WHERE id=?");

            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setInt(2, updatedPerson.getAge());
            preparedStatement.setString(3, updatedPerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Person personToBeUpdated = getById(id);
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "DELETE FROM person WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        people.removeIf(p -> p.getId() == id);
    }
}
