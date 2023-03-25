package ua.bugaienko.FirstSecurityApp.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author Sergii Bugaienko
 */

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "Name should not be empty")
    private String userName;

    @Column(name = "year_of_birthday")
    @Min(value = 1900, message = "Year of birthday should be greater than 1900")
    private int yearOfBirthday;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public Person() {
    }

    public Person(String userName, int yearOfBirthday) {
        this.userName = userName;
        this.yearOfBirthday = yearOfBirthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(int yearOfBirthday) {
        this.yearOfBirthday = yearOfBirthday;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", yearOfBirthday=" + yearOfBirthday +
                " }";
    }
}
