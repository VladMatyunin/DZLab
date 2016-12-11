package ru.dz.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.dz.entity.Award;
import ru.dz.entity.Movie;
import ru.dz.entity.People;


import java.io.IOException;
import java.net.URL;
import java.util.List;

public class AutoCompite {
    private static final ObjectMapper mapper = new ObjectMapper();
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Movie> loadMoviesFromJson() {
        List<Movie> movies = null;
        try {
            movies = mapper.readValue(getResource("static/resources/movies.json"), mapper.getTypeFactory().constructCollectionType(List.class, Movie.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<People> loadPeopleFromJson() {
        List<People> people = null;
        try {
            people = mapper.readValue(getResource("static/resources/actors.json"), mapper.getTypeFactory().constructCollectionType(List.class, People.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return people;
    }

    public List<Award> loadAwardsFromJson() {
        List<Award> awards = null;
        try {
            awards = mapper.readValue(getResource("static/resources/awards.json"), mapper.getTypeFactory().constructCollectionType(List.class, Award.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return awards;
    }

    private String getResource(String file) {
        URL url = Resources.getResource(file);
        try {
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            return "";
        }
    }
}
