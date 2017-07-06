package com.codecool.yummy.database;

import com.codecool.yummy.model.Recipe;

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
    private List<Recipe> recipes = new ArrayList<Recipe>();

    @ManyToMany
    private Set<UserORMEntity> following = new HashSet<UserORMEntity>();

    @ManyToMany
    private Set<UserORMEntity> followers = new HashSet<UserORMEntity>();

    public UserORMEntity() {
    }

    public UserORMEntity(String name, String password, String email, List<Recipe> recipes, Set<UserORMEntity> following, Set<UserORMEntity> followers) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.recipes = recipes;
        this.following = following;
        this.followers= followers;
    }
}
