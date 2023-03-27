package ua.bugaienko.first.domain;

import javax.persistence.*;

/**
 * @author Sergii Bugaienko
 */

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;



    public Person(String surname, String name) {
        this.surname = surname;
        this.name = name;

    }
    public Person(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
