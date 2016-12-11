package ru.dz.entity;

import ru.dz.entity.enums.UserRole;
import ru.dz.entity.interfaces.MyObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements MyObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(length = 40)
    private String password;

    @Column(unique = true)
    private String email;

    private Integer vkontakte_id;

    private String photo;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    private List<Review> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getVkontakte_id() {
        return vkontakte_id;
    }

    public void setVkontakte_id(Integer vkontakte_id) {
        this.vkontakte_id = vkontakte_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

