package com.codecool.yummy.model;

import javax.jws.soap.SOAPBinding;
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

    @ManyToMany
    private Set<User> followers = new HashSet<User>();

    public User() {
    }

    public User(String name, String password, List<Recipe> recipes, Set<User> following,Set<User> followers) {
        this.name = name;
        this.password = password;
        this.recipes = recipes;
        this.following = following;
        this.followers= followers;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void addFollowers(User user){
        followers.add(user);
    }

    public void removeFollowing(User user) {
        following.remove(user);
    }

    public void removeFollowers(User user){
        followers.remove(user);
    }

}
