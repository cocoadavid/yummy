package com.codecool.yummy.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by szilarddavid on 2017.07.06..
 */
@Entity
public class PictureORMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String path;

    public PictureORMEntity() {}

    public PictureORMEntity(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
