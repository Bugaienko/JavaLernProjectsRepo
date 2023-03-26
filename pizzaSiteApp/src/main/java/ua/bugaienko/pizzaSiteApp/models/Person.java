package ua.bugaienko.pizzaSiteApp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sergii Bugaienko
 */

@Entity
@Table(name = "person")
public class Person {

    @Id
    private int id;

    private String username;
    private String password;
    private String email;
    private String role;
    private String avatar;

}
