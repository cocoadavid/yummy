package com.codecool.yummy.controller;

import com.codecool.yummy.database.DBHandler;
import com.codecool.yummy.database.RecipeORMEntity;
import com.codecool.yummy.database.UserORMEntity;
import com.codecool.yummy.database.UserORMRepository;
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

        RecipeORMEntity recept = new RecipeORMEntity();
        recept.setName("lasagne");
        dbHandler.saveRecipe(recept);

        UserORMEntity user1 = new UserORMEntity();
        user1.setName("user1");
        user1.setPassword("password");
        user1.addRecipe(receptecske);
        dbHandler.saveUser(user1);

        UserORMEntity user2 = new UserORMEntity();
        user2.setName("user2");
        user2.addRecipe(recept);
        dbHandler.saveUser(user2);





        return "hambi-mambi";
    }
}
