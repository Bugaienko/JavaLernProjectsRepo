package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration =
                new Configuration().addAnnotatedClass(Actor.class)
                        .addAnnotatedClass(Movie.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            //Create new movie and actors
//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("Harvey Keitel", 81);
//            Actor actor2 = new Actor("Samuel L. Jackson", 72);
//            //List.of() from 9 Java
//            movie.setActors(new ArrayList<>(Arrays.asList(actor1, actor2)));
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

//            //Get actors from movie
//            Movie movie = session.get(Movie.class, 1);
//            List<Actor> actors = movie.getActors();
//            System.out.println(actors);

//            //Add new movie to Actor
//            Movie movie = new Movie("Reservoir Dogs", 1992);
//            Actor actor = session.get(Actor.class, 1);
//            movie.setActors(new ArrayList<>(Collections.singleton(actor)));
//            actor.getMovies().add(movie);
//            session.save(movie);

            //Remove link between actor and movie
            Actor actor = session.get(Actor.class, 2);
            System.out.println(actor.getMovies());
            Movie movieToRemove = actor.getMovies().get(0);

            actor.getMovies().remove(0);
            movieToRemove.getActors().remove(actor); // need HashCode and Equals methods




            session.getTransaction().commit();

        }


    }
}
