package org.example;

import org.example.model.Item;
import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration =
                new Configuration().addAnnotatedClass(Person.class)
                        .addAnnotatedClass(Item.class).addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            person.setPassport(new Passport(1234321));




            // @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
//            session.persist(person); // Hibernate + session.persist(item)

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }


    }
}
