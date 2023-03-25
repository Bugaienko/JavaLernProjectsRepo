package ua.bugaienko.library.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Sergii Bugaienko
 */

public class Book {
    private int id;
    @NotEmpty
    @Size(min = 1, message = "Name should be greater than 2")
    private String name;
    @NotEmpty
    @Size(min = 1, message = "Author should be greater than 2")
    private String author;
    @Min(value = 1000, message = "Year should be greater than 0" )
    private int year;

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public  Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
