package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.dz.entity.Movie;
import ru.dz.entity.Rating;
import ru.dz.entity.Review;
import ru.dz.entity.User;
import ru.dz.repository.*;

import java.util.Date;

/**
 * Created by Vlad.M on 08.12.2016.
 */
@Controller
@RequestMapping(value = "/movie")
public class FilmInformController {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    RatingRepository ratingRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String loadFilm(@PathVariable("id") Long id, ModelMap modelMap) {
        Movie movie = movieRepository.findOne(id);
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            modelMap.addAttribute("access", 0);
            modelMap.addAttribute("canvote", 0);
        } else {
            User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            if (ratingRepository.findByMovie(movie).contains(user)) modelMap.addAttribute("canvote", "0");
            else modelMap.addAttribute("canvote", 1);
            modelMap.addAttribute("access", 1);

        }
        modelMap.addAttribute("comments", movieRepository.findOne(id).getReviews());
        modelMap.addAttribute("movie", movieRepository.findOne(id));
        return "movie";
    }

    @RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
//    @Secured("ROLE_USER")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public Review addComment(@PathVariable("id") Long id, @RequestParam("content") String text) {
        System.out.println(text);
        Review review = new Review();
        review.setDate(new Date());
        review.setReview(text);
        Movie movie = movieRepository.findOne(id);
        review.setMovie(movie);
        review.setUser(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        commentRepository.save(review);

        movie.getReviews().add(review);
        movieRepository.saveAndFlush(movie);
        return review;
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/rate")
    public Float rate(@PathVariable("id") Long id, @RequestParam("value") Integer value) {
        Rating rating = new Rating();
        Movie movie = movieRepository.findOne(id);
        rating.setUser(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        rating.setMovie(movie);
        rating.setRating(value);
        rating = ratingRepository.saveAndFlush(rating);
        movie.getRatings().add(rating);
        movie.setRating_num(movie.getRating_num() + value);
        movie.setVoted_number(movie.getVoted_number() + 1);
        return (float) (movie.getRating_num() / movie.getVoted_number());
    }
}

