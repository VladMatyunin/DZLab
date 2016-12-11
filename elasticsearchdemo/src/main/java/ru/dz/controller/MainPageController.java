package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.elastic.MovieSearchService;
import ru.dz.entity.Movie;
import ru.dz.repository.AutoCompite;
import ru.dz.repository.MovieRepository;
import ru.dz.repository.RatingRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Vlad.M on 07.12.2016.
 */
@Controller
public class MainPageController {
    @Autowired
    MovieSearchService movieSearchService;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    RatingRepository ratingRepository;

    @PostConstruct
    private void init() {
//        AutoCompite autoCompite = new AutoCompite();
//        movieRepository.save(autoCompite.loadMoviesFromJson());
        ArrayList<Movie> movies = (ArrayList<Movie>) movieRepository.findAll();
        for (Movie movy : movies) {
            if (Objects.equals(movy.getImage(), null)) {
                movy.setImage("/resources/images/default_film.jpg");
                movieRepository.saveAndFlush(movy);
            }
            movieSearchService.add(movy);
        }
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String loadpage() {

        return "main";
    }
}
