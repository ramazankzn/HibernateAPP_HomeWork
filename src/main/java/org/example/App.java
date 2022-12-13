package org.example;

import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class).addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {

            session.beginTransaction();

            //получите любого режиссера, а затем получите список
            //его фильмов.

            Director director = session.get(Director.class, 2);

            System.out.println(director);
            List<Movie> movieList = director.getMovies();
            for (Movie movie : movieList
            ) {
                System.out.println(movie);
            }

            //Получите любой фильм, а затем получите его режиссера.
            Movie movie = session.get(Movie.class, 5);
            System.out.println(movie);
            System.out.println(movie.getDirector());

            //Добавьте еще один фильм для любого режиссера.

            Movie newMovie = new Movie(director, "Молчание", 2016);
            System.out.println("Новый фильм: " + newMovie);
            session.save(newMovie);

            //Создайте нового режиссера и новый фильм и свяжите эти сущности.

            Director newDirector = new Director("Юрий Быков", 1981);
            session.save(newDirector);
            Movie newMovie2 = new Movie(newDirector, "Дурак", 2014);
            session.save(newMovie2);
            List<Movie> movieList1 = new ArrayList<>();
            movieList1.add(newMovie2);
            newDirector.setMovies(movieList1);
            System.out.println(newDirector);
            newDirector.printMovieList();

            //Смените режиссера у существующего фильма.
            System.out.println("Теперь список такой:");
            Movie changeDirector = session.get(Movie.class, 4);
            changeDirector.setDirector(newDirector);
            newDirector.getMovies().add(changeDirector);
            newDirector.printMovieList();

            //Удалите фильм у любого режиссера.
            Movie deleteMovie = session.get(Movie.class, 12);
            deleteMovie.getDirector().getMovies().remove(deleteMovie);
            session.remove(deleteMovie);
            System.out.println("Список после удаления:");
            deleteMovie.getDirector().printMovieList();


            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }

}
