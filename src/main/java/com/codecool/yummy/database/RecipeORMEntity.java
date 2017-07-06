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
    private List<Picture> pictures = new ArrayList<Picture>();

    @OneToMany
    private List<Comment> comments = new ArrayList<Comment>();

    private int yummy;

    @ManyToOne
    private User user;

    public RecipeORMEntity() {}

    public RecipeORMEntity(String name, String description, String category, Date date, List<Picture> pictures, List<Comment> comments, int yummy, User user) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.date = date;
        this.pictures = pictures;
        this.comments = comments;
        this.yummy = yummy;
        this.user = user;
    }
}
