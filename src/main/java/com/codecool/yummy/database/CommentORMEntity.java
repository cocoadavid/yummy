package com.codecool.yummy.database;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by szilarddavid on 2017.07.06..
 */

@Entity
public class CommentORMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private UserORMEntity user;

    private Date date;

    private String comment;

    public CommentORMEntity() {}

    public CommentORMEntity(UserORMEntity user, Date date, String comment) {
        this.user = user;
        this.date = date;
        this.comment = comment;
    }
}
