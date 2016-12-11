package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
