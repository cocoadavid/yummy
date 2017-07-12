package com.codecool.yummy.model;

import javax.persistence.*;

/**
 * Created by szilarddavid on 2017.07.12..
 */
@Entity(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="picture_id")
    private long id;

    private String name;

    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
