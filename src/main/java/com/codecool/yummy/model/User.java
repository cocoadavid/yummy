package com.codecool.yummy.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by szilarddavid on 2017.06.21..
 */
@Entity(name = "person")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String password;

    @OneToMany
    private List<Recipe> recipes = new ArrayList<Recipe>();

    @ManyToMany
    private Set<User> following = new HashSet<User>();

    public User() {
    }

    public User(String name, String password, List<Recipe> recipes, Set<User> following) {
        this.name = name;
        this.password = password;
        this.recipes = recipes;
        this.following = following;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void addFollowing(User user) {
        following.add(user);
    }

}
