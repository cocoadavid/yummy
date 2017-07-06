package com.codecool.yummy.controller;

import com.codecool.yummy.database.DBHandler;
import com.codecool.yummy.database.RecipeORMEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by szilarddavid on 2017.07.06..
 */
@RestController
public class APIRequestHandler {

    private DBHandler dbHandler;

    @Autowired
    public APIRequestHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @RequestMapping("/burger")
    public String queryDB(@RequestParam(value="name", required=false, defaultValue="Smith") String name) {

        RecipeORMEntity receptecske = new RecipeORMEntity();
        receptecske.setName("hamburger");
        dbHandler.saveRecipe(receptecske);

        return "hambi-mambi";
    }
}
