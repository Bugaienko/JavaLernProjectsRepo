package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration =
                new Configuration().addAnnotatedClass(Person.class)
                        .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            Person person = session.get(Person.class, 3);
//            System.out.println(person);
//            List<Item> items = person.getItems();
//            System.out.println(items);

//            Item item = session.get(Item.class, 5);
//            System.out.println(item);
//            System.out.println(item.getOwner());
//

//            Person person = session.get(Person.class, 2);
//            Item newItem = new Item("Item from  Hibernate", person);
//            // Not for DB
//            person.getItems().add(newItem);
//            session.save(newItem);

//            Person person = new Person("Test Name", 25);
//            Item newItem = new Item("Some product", person);
//            person.setItems(new ArrayList<Item>(Collections.singletonList(newItem)));
//            session.save(person);
//            session.save(newItem);

//            Person person = session.get(Person.class, 2);
//            List<Item> items = person.getItems();
//            for (Item item: items) {
//                session.remove(item);
//            }
//            person.getItems().clear();

//            Person person = session.get(Person.class, 4);
//            session.remove(person);
//            person.getItems().forEach(i->i.setOwner(null));

            Person person = session.get(Person.class, 2);
            Item item = session.get(Item.class, 1);
            //For hibernate cash
            item.getOwner().getItems().remove(item);
            //For BD 1 line - SQL
            item.setOwner(person);
            //For hibernate cash
            person.getItems().add(item);


            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }


    }
}
