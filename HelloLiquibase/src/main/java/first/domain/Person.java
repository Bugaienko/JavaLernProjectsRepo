package first.domain;

import javax.persistence.*;

/**
 * @author Sergii Bugaienko
 */

@Entity
@Table(name = "persons2")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
//    @Column(name = "surname")
    private String surName;
    @Column(name = "name")
    private String name;



    public Person(String surName, String name) {
        this.surName = surName;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surname) {
        this.surName = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
