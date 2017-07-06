package com.codecool.yummy.controller;

import com.codecool.yummy.database.UserORMEntity;
import com.codecool.yummy.model.Comment;
import com.codecool.yummy.model.Picture;
import com.codecool.yummy.model.Recipe;
import com.codecool.yummy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by flavia on 2017.07.06..
 */
@Controller
public class HTTPRequestHandler {

    private User user;
    private Recipe recipe;
    private Picture picture;
    private Comment comment;

    @Autowired
    public HTTPRequestHandler(User user, Recipe recipe, Picture picture, Comment comment) {
        this.user = user;
        this.recipe = recipe;
        this.picture = picture;
        this.comment = comment;
    }

//    @RequestMapping(value="/login",method= RequestMethod.GET)
//    public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView model = new ModelAndView("login");
//        UserORMEntity userEntity = new UserORMEntity();
//        model.addObject("loginBean", userEntity);
//        return model;
//    }
//
//    @RequestMapping(value="/login",method=RequestMethod.POST)
//    public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")UserORMEntity userEntity) {
//        ModelAndView model= null;
//        try {
//            boolean isValidUser = user.isValidUser(userEntity.getName(), userEntity.getPassword());
//            if(isValidUser) {
//                request.setAttribute("loggedInUser", userEntity.getName());
//                model = new ModelAndView("welcome");
//            } else {
//                model = new ModelAndView("login");
//                model.addObject("loginBean", userEntity);
//                request.setAttribute("message", "Invalid credentials!!");
//            }
//        } catch(Exception e) {}
//        return model;
//    }
}
