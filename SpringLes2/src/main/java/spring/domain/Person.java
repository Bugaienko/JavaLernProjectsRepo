package spring.domain;

/**
 * @author Sergii Bugaienko
 */

public class Person {
    private String surname;
    private String name;
    private  int id;
    private static  int counter = 0;

    public Person(String surname, String name) {
        this.surname = surname;
        this.name = name;
        this.id = ++counter;
    }
    public Person(){

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = ++counter;
    }
}
