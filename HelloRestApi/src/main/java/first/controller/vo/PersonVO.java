package first.controller.vo;

import first.domain.Person;

import javax.persistence.Column;

/**
 * @author Sergii Bugaienko
 */

public class PersonVO {
    private  int id;
    private String surName;
    private String name;

    public PersonVO(int id, String surName, String name) {
        this.id = id;
        this.surName = surName;
        this.name = name;
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

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static PersonVO valueOf(Person person){
        return new PersonVO(person.getId(), person.getSurName(), person.getName());
    }
}
