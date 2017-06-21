package com.codecool.yummy.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by flavia on 2017.06.21..
 */
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private String category;

    private Date date;

    @OneToMany
    @JoinTable(
            name = "PictureToRecipe",
            joinColumns = @JoinColumn(name = "Recipe"),
            inverseJoinColumns = @JoinColumn(name = "Picture"))
    private Set<Picture> picture = new HashSet<Picture>();

    @OneToMany
    private Set<Comment> comment = new HashSet<Comment>();

    private int yummy;

    @ManyToOne
    private User user;

    public Recipe() {}

    public Recipe(String description, String category, Date date, Set<Picture> picture, Set<Comment> comment, int yummy, User user) {
        this.description = description;
        this.category = category;
        this.date = date;
        this.picture = picture;
        this.comment = comment;
        this.yummy = yummy;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Picture> getPicture() {
        return picture;
    }

    public void setPicture(Set<Picture> picture) {
        this.picture = picture;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public int getYummy() {
        return yummy;
    }

    public void setYummy(int yummy) {
        this.yummy = yummy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
