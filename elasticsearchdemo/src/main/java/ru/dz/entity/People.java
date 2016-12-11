package ru.dz.entity;


import ru.dz.entity.enums.Role;
import ru.dz.entity.interfaces.MyObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class People implements MyObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String surname;

    @Column(length = 50)
    private String patronymic;

    private Integer age;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "peoples")
    private List<Movie> movies;

//    @ManyToMany
//    @JoinTable(name = "people_award",
//            joinColumns = @JoinColumn(name = "people_id"),
//            inverseJoinColumns = @JoinColumn(name = "award_id"))
    @OneToMany(mappedBy = "people", cascade = CascadeType.REFRESH)
    private List<Award> awards;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
