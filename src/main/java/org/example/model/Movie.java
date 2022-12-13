package org.example.model;

import jakarta.persistence.*;

@Entity
@Table (name = "Movie")
public class Movie {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "director_id")
    private Director director;
    @Column (name = "name")
    private String name;
    @Column (name = "year_of_production")
    private int yearOfProduction;

    public Movie(){}
    public Movie(Director director, String name, int yearOfProduction) {
        this.director = director;
        this.name = name;
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public String toString() {
        return "Фильм " + name +
                ", режиссер " + director +
                ", год: " + yearOfProduction +
                '.';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }
}
