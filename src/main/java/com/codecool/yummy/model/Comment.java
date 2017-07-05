package com.codecool.yummy.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by flavia on 2017.06.21..
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    private Date date;

    private String comment;

    public Comment() {}

    public Comment(User user, Date date, String comment) {
        this.user = user;
        this.date = date;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
