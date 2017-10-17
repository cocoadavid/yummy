package com.codecool.yummy.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by szilarddavid on 2017.07.12..
 */

@Entity(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_id")
    private long id;

    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    private String category;

    private Date date;

    @OneToMany
    private List<Picture> pictures = new ArrayList<Picture>();

    @OneToMany
    private List<Comment> comments = new ArrayList<Comment>();

    private int yummy;

    @ManyToMany
    private List<User> yummers = new ArrayList<>();

//    @ManyToOne(cascade = {CascadeType.ALL})
    @ManyToOne
    private User user;

    public Recipe(){}

    /////////////// GETTERS - SETTERS //////////////////////

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

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    public void addPicture(Picture picture) {
        pictures.add(picture);
    }

    public void removePicture(Picture picture) {
        pictures.remove(picture);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    public void addYummer(User user){
        if(yummers.contains(user)){
            yummers.remove(user);
            yummy--;
        }
        else {
            yummers.add(user);
            yummy++;
        }
    }
}
