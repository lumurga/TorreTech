package com.torre.tech.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name= "types")
public class Type {

    /* Attributes */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_type")
    private Long id;
    private String title;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="type_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Skill> skills = new HashSet<>();

    /* Constructor */
    public Type() {
    }

    public Type(String title) {
        this.title = title;
    }

    /* Getters and Setters */

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
}
