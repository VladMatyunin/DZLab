package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Movie;
import ru.dz.entity.Rating;
import ru.dz.entity.User;


import java.util.List;

/**
 * Created by Vlad.M on 10.12.2016.
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<User> findByMovie(Movie movie);
}
