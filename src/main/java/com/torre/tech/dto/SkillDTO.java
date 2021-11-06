package com.torre.tech.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.torre.tech.model.User;


import java.util.HashSet;
import java.util.Set;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillDTO {

    /* Attributes */
    private Long id;
    private String name;

    private Set<User> users;

    /* Constructor */
    public SkillDTO() {
    }

    public SkillDTO(String name, Set<User> users) {
        this.name = name;
      
        this.users = new HashSet<>();
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



    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
