package com.codecool.yummy.database;

import com.codecool.yummy.model.Recipe;
import com.codecool.yummy.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by szilarddavid on 2017.07.06..
 */
@Entity(name = "person")
public class UserORMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String password;

    private String email;

    @OneToMany
    private List<RecipeORMEntity> recipes = new ArrayList<RecipeORMEntity>();

    @ManyToMany
    private Set<UserORMEntity> following = new HashSet<UserORMEntity>();

    @ManyToMany
    private Set<UserORMEntity> followers = new HashSet<UserORMEntity>();

    public UserORMEntity() {
    }

    public UserORMEntity(String name, String password, String email, List<RecipeORMEntity> recipes, Set<UserORMEntity> following, Set<UserORMEntity> followers) {
        this.name = name;
        this.password = password;
        this.email = email;
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

    public List<RecipeORMEntity> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeORMEntity> recipes) {
        this.recipes = recipes;
    }

    public Set<UserORMEntity> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserORMEntity> following) {
        this.following = following;
    }

    public void addRecipe(RecipeORMEntity recipe) {
        recipes.add(recipe);
    }

    public void addFollowing(UserORMEntity user) {
        following.add(user);
    }

    public void addFollowers(UserORMEntity user){
        followers.add(user);
    }

    public void removeFollowing(UserORMEntity user) {
        following.remove(user);
    }

    public void removeFollowers(UserORMEntity user){
        followers.remove(user);
    }
}
