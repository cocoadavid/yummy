package com.codecool.yummy.controller;

import com.codecool.yummy.model.Recipe;
import com.codecool.yummy.model.User;
import com.codecool.yummy.service.RecipeService;
import com.codecool.yummy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by szilarddavid on 2017.07.11..
 */

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        User usernameExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user",
                            "There is already a user registered with the email provided.");
        }
        if (usernameExists != null) {
            bindingResult.rejectValue("username", "error.user",
                    "There is already a user registered with this username.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", 1);
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("userMessage","Content Available Only for Users");
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/new_recipe", method = RequestMethod.GET)
    public ModelAndView newRecipe() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Recipe recipe = new Recipe();
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("legend", "New Recipe");
        modelAndView.addObject("button", "Create Recipe");
        modelAndView.setViewName("recipe_form");
        return modelAndView;
    }

    @RequestMapping(value = "/new_recipe", method = RequestMethod.POST)
    public ModelAndView createNewRecipe(@Valid Recipe recipe, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("recipe_form");
        } else {
            recipe.setUser(user);
            recipeService.saveRecipe(recipe);
            user.addRecipe(recipe);
            userService.updateUser(user);
            modelAndView.addObject("success", 1);
            modelAndView.addObject("legend", "New Recipe");
            modelAndView.addObject("username", user.getUsername());
            modelAndView.addObject("button", "Create Recipe");
            modelAndView.setViewName("recipe_form");
        }
        return modelAndView;
    }
}
