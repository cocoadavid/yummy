package com.codecool.yummy.model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by flavia on 2017.06.21..
 */
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private String category;

    private Date date;

    @OneToMany
//    @JoinTable(
//            name = "PictureToRecipe",
//            joinColumns = @JoinColumn(name = "Recipe"),
//            inverseJoinColumns = @JoinColumn(name = "Picture"))
    private List<Picture> pictures = new ArrayList<Picture>();

    @OneToMany
    private List<Comment> comments = new ArrayList<Comment>();

    private int yummy;

    @ManyToOne
    private User user;

    public Recipe() {}

    public Recipe(String name, String description, String category, Date date, List<Picture> pictures, List<Comment> comments, int yummy, User user) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.date = date;
        this.pictures = pictures;
        this.comments = comments;
        this.yummy = yummy;
        this.user = user;
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

    public List<Picture> getPicture() {
        return pictures;
    }

    public void setPicture(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Comment> getComment() {
        return comments;
    }

    public void setComment(List<Comment> comments) {
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

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removePicture(Picture picture) {
        pictures.remove(picture);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

}
