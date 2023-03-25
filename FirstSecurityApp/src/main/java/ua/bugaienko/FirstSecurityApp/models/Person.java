package ua.bugaienko.FirstSecurityApp.models;

import javax.persistence.*;

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
    private String userName;

    @Column(name = "year_of_birthday")
    private int yearOfBirthday;

    @Column(name = "password")
    private String password;

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
