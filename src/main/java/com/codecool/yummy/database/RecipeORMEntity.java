package com.codecool.yummy.database;

import com.codecool.yummy.model.Comment;
import com.codecool.yummy.model.Picture;
import com.codecool.yummy.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by szilarddavid on 2017.07.06..
 */
@Entity
public class RecipeORMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private String category;

    private Date date;

    @OneToMany
    private List<PictureORMEntity> pictures = new ArrayList<PictureORMEntity>();

    @OneToMany
    private List<CommentORMEntity> comments = new ArrayList<CommentORMEntity>();

    private int yummy;

    @ManyToOne
    private UserORMEntity user;

    public RecipeORMEntity() {}

    public RecipeORMEntity(String name, String description, String category, Date date, List<PictureORMEntity> pictures, List<CommentORMEntity> comments, int yummy, UserORMEntity user) {
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

    public List<PictureORMEntity> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureORMEntity> pictures) {
        this.pictures = pictures;
    }

    public List<CommentORMEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentORMEntity> comments) {
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

    public List<PictureORMEntity> getPicture() {
        return pictures;
    }

    public void setPicture(List<PictureORMEntity> pictures) {
        this.pictures = pictures;
    }

    public List<CommentORMEntity> getComment() {
        return comments;
    }

    public void setComment(List<CommentORMEntity> comments) {
        this.comments = comments;
    }

    public int getYummy() {
        return yummy;
    }

    public void setYummy(int yummy) {
        this.yummy = yummy;
    }

    public UserORMEntity getUser() {
        return user;
    }

    public void setUser(UserORMEntity user) {
        this.user = user;
    }

    public void addPicture(PictureORMEntity picture) {
        pictures.add(picture);
    }

    public void addComment(CommentORMEntity comment) {
        comments.add(comment);
    }

    public void removePicture(PictureORMEntity picture) {
        pictures.remove(picture);
    }

    public void removeComment(CommentORMEntity comment) {
        comments.remove(comment);
    }
}
