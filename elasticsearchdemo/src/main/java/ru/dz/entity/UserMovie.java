package ru.dz.entity;

import java.io.Serializable;
import java.util.Objects;


public class UserMovie implements Serializable {

    private User user;

    private Movie movie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMovie userMovie = (UserMovie) o;
        return Objects.equals(user, userMovie.user) &&
                Objects.equals(movie, userMovie.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, movie);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
