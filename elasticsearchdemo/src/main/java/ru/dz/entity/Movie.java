package ru.dz.entity;
import ru.dz.entity.enums.Genre;
import ru.dz.entity.interfaces.MyObject;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Movie implements MyObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(length = 4)
    private int year;

    @Column(length = 2)
    private int age;

    private String image = "../../resources/images/single.jpg";

    private String trailer;

    private String country;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "movies_actors",
           joinColumns = @JoinColumn(name = "movie_id"),
           inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<People> peoples;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REFRESH)
    private List<Rating> ratings;

    private Integer rating_num = 0;

    private Integer voted_number = 0;

    private Integer sum_rating = 0;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REFRESH)
    private List<Review> reviews;

//    @ManyToMany
//    @JoinTable(name = "movie_awards",
//            joinColumns = @JoinColumn(name = "movie_id"),
//            inverseJoinColumns = @JoinColumn(name = "award_id"))
    @OneToMany(mappedBy = "movie", cascade = CascadeType.REFRESH)
    private List<Award> awards;

    public Integer getRating_num() {
        return rating_num;
    }

    public void setRating_num(Integer rating_num) {
        this.rating_num = rating_num;
    }

    public Integer getVoted_number() {
        return voted_number;
    }

    public void setVoted_number(Integer voted_number) {
        this.voted_number = voted_number;
    }

    public Integer getSum_rating() {
        return sum_rating;
    }

    public void setSum_rating(Integer sum_rating) {
        this.sum_rating = sum_rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }
}
