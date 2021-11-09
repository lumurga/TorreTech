package com.torre.tech.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.torre.tech.model.Skill;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeDTO {

    /* Attributes */
    private Long id;
    private String title;
    private Set<Skill> skills;


    /* Constructor */
    public TypeDTO() {
    }

    public TypeDTO(String title, Set<Skill> skills) {
        this.title = title;
        this.skills = new HashSet<>();
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

