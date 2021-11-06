package com.torre.tech.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    /* Attributes */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String urlImage;


    @JoinTable(name = "users_skills",
            joinColumns = @JoinColumn(name = "id_user"),//, nullable = false
            inverseJoinColumns = @JoinColumn(name = "id_skill")//, nullable = false
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Skill> skills = new HashSet<>();

    /* Constructor */

    public User() {
    }

    public User(String name, String urlImage, Set<Skill> skills) {
        this.name = name;
        this.urlImage = urlImage;
        this.skills = skills;
    }

    /* Getters and Setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
