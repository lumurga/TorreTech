package com.torre.tech.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.torre.tech.model.Skill;


import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    /* Attributes */
    private Long id;
    private String name;
    private String urlImage;
    private Set<Skill> skills = new HashSet<>();

    /* Constructor */

    public UserDTO() {
    }

    public UserDTO(Long id, String name,String urlImage, Set<Skill> skills) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.skills = skills;
    }

    /* Getters and Setters */

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
