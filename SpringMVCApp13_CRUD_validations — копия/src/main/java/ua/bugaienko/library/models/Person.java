package ua.bugaienko.library.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author Sergii Bugaienko
 */

public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]\\w+", message = "Name should begin with a capital letter")
    private String fullName;
    @Min(value = 1900, message = "Year of birthday should be greater than 1900")
    private int yearOfBirth;

    public Person(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
