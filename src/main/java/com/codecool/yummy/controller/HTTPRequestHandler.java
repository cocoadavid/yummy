package com.codecool.yummy.controller;

import com.codecool.yummy.database.DBHandler;
import com.codecool.yummy.database.RecipeORMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HTTPRequestHandler {

    private RecipeORMRepository recipeORMRepository;

    @Autowired
    private DBHandler dbHandler;

    @Autowired
    HTTPRequestHandler(RecipeORMRepository recipeORMRepository) {
        this.recipeORMRepository = recipeORMRepository;
    }

    @RequestMapping("/feed")
    public String requestThatReturnsTemplate(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {

        model.addAttribute("name", recipeORMRepository.findRecipeByName("hamburger").get(0).getName());
        return "feed"; // The name of the template
    }

}
