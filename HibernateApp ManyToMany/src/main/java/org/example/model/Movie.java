package org.example.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Sergii Bugaienko
 */

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_production")
    private int ageOfProduction;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;

    public Movie() {
    }

    public Movie(String name, int ageOfProduction) {
        this.name = name;
        this.ageOfProduction = ageOfProduction;
    }

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

    public int getAgeOfProduction() {
        return ageOfProduction;
    }

    public void setAgeOfProduction(int ageOfProduction) {
        this.ageOfProduction = ageOfProduction;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (ageOfProduction != movie.ageOfProduction) return false;
        return Objects.equals(name, movie.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + ageOfProduction;
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ageOfProduction=" + ageOfProduction +
                '}';
    }
}
