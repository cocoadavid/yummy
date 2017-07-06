package com.codecool.yummy.controller;

import com.codecool.yummy.database.DBHandler;
import com.codecool.yummy.database.RecipeORMRepository;
import com.codecool.yummy.database.UserORMEntity;
import com.codecool.yummy.model.Comment;
import com.codecool.yummy.model.Picture;
import com.codecool.yummy.model.Recipe;
import com.codecool.yummy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
