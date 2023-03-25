package hibernate;

import hibernate.domain.Person;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author Sergii Bugaienko
 */

public class EntityManagerDemo {
    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        Person person = new Person("Sidor", "Sidorov");

        //добавляем
//        em.getTransaction().begin();
//        em.persist(person);
//        em.getTransaction().commit();

        em.getTransaction().begin();
        Person person1 = em.find(Person.class, 4);
        em.getTransaction().commit();
        System.out.println(person1);
        person1.setFirstname("Artem");
        em.getTransaction().begin();
        em.merge(person1);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Person person2 = em.find(Person.class, 6);
        em.remove(person2);
        em.getTransaction().commit();

    }
}
