package com.codecool.yummy.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by szilarddavid on 2017.06.21..
 */
@Entity
        (name = "person")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String password;

    @OneToMany
    private Set<Recipe> recipe = new HashSet<Recipe>();

    @ManyToMany
    private Set<User> following = new HashSet<User>();

    public User() {}

    public User(String name, String password, Set<Recipe> recipe, Set<User> following) {
        this.name = name;
        this.password = password;
        this.recipe = recipe;
        this.following = following;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Recipe> getRecipe() {
        return recipe;
    }

    public void setRecipe(Set<Recipe> recipe) {
        this.recipe = recipe;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

}
