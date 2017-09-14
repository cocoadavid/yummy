package com.codecool.yummy.model;

import java.util.*;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "email")
    @Email(message = "*Please provide a valid email.")
    @NotEmpty(message = "*Please provide an email.")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters.")
    @NotEmpty(message = "*Please provide your password.")
    @Transient
    private String password;

    @Column(name = "username")
    @NotEmpty(message = "*Please provide your username.")
    private String username;

    @Column(name = "first_name")
    @NotEmpty(message = "*Please provide your first name.")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name.")
    private String lastName;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "recipes")
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Recipe> recipes;

    @Column(name = "followers")
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<User> followers;

    @Column(name = "following")
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<User> following;

    /////////////// GETTERS - SETTERS //////////////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    /////////////// SUPPORTING METHODS //////////////////////

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);;
    }

    public void removeRecipe(Recipe recipe) { recipes.remove(recipe); }

    public void addFollower(User user) { followers.add(user); }

    public void removeFollower(User user) { followers.remove(user); }

    public void addFollowing(User user) {
        if (this.getUsername().equals(user.getUsername()) == false && this.following.contains(user) == false ){
            following.add(user);
        }
    }

    public void removeFollowing(User user) { following.remove(user); }

    public List<Recipe> getFollowedRecipes() {
        List<Recipe> recipes = new ArrayList<Recipe>();

        for (Recipe recipe : this.getRecipes()) {
            recipes.add(recipe);
        }

        for (User user : following) {
            for (Recipe recipe : user.getRecipes()) {
                recipes.add(recipe);
            }
        }

        Collections.sort(recipes, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        return recipes;
    }

    public int getNumberOfRecipes(List<Recipe> recipes) { return recipes.size(); }

    public int getNumberOfFollowers(List<User> followers) { return followers.size(); }

    public int getNumberOfFollowing(List<User> following) { return following.size(); }

}
