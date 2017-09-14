package com.codecool.yummy.controller;

import com.codecool.yummy.model.Recipe;
import com.codecool.yummy.model.User;
import com.codecool.yummy.service.RecipeService;
import com.codecool.yummy.service.UserService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.json.Json;
import javax.json.JsonObject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
        modelAndView.addObject("recipes", user.getFollowedRecipes());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value="/search/{searchTerm}", method = RequestMethod.GET)
    @ResponseBody
    public String search(@PathVariable String searchTerm) throws JSONException {
        List<User> searchedUsers = userService.findByUsernameContaining(searchTerm);
        List<String> users = new ArrayList<String>();
        for (User searchedUser : searchedUsers) {
            users.add(searchedUser.getUsername());
        }
        JSONArray array = new JSONArray(users);
        JsonObject response = Json.createObjectBuilder()
                .add("searchedUsers", array.toString())
                .build();
        return response.toString();
    }

    @RequestMapping(value="/myprofile/{username}", method = RequestMethod.GET)
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("recipes", user.getRecipes());
        modelAndView.addObject("numberOfRecipes", user.getNumberOfRecipes(user.getRecipes()));
        modelAndView.addObject("numberOfFollowers", user.getNumberOfFollowers(user.getFollowers()));
        modelAndView.addObject("numberOfFollowing", user.getNumberOfFollowing(user.getFollowing()));
        modelAndView.setViewName("my_profile");
        return modelAndView;
    }

    @RequestMapping(value="/profile/{username}", method = RequestMethod.GET)
    public ModelAndView profile(@PathVariable("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        User otherUser = userService.findUserByUsername(username);
        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("otherUsername", otherUser.getUsername());
        modelAndView.addObject("recipes", otherUser.getRecipes());
        modelAndView.addObject("numberOfRecipes", otherUser.getNumberOfRecipes(otherUser.getRecipes()));
        modelAndView.addObject("numberOfFollowers", otherUser.getNumberOfFollowers(otherUser.getFollowers()));
        modelAndView.addObject("numberOfFollowing", otherUser.getNumberOfFollowing(otherUser.getFollowing()));
        modelAndView.setViewName("user_profile");
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
            Recipe returnedRecipe = recipeService.saveRecipe(recipe);
            Long recipeId = returnedRecipe.getId();
            System.out.println(recipeId);
            user.addRecipe(recipe);
            userService.updateUser(user);
            modelAndView.addObject("success", 1);
            modelAndView.addObject("legend", "New Recipe");
            modelAndView.addObject("username", user.getUsername());
            modelAndView.addObject("button", "Upload");
            modelAndView.addObject("recipeId", recipeId + ".jpg");
            modelAndView.setViewName("uploadImage");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
    public ModelAndView showRecipe(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Recipe recipe = recipeService.findRecipeById(id);
        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("recipeId", id);
        modelAndView.setViewName("recipe");
        return modelAndView;
    }

    @RequestMapping(value = "/yummy/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String yummy(@PathVariable(value = "id") Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Recipe recipe = recipeService.findRecipeById(id);
        recipe.addYummer(user);
        Recipe updatedRecipe = recipeService.updateRecipe(recipe);
        int yummies = updatedRecipe.getYummy();
        JsonObject response = Json.createObjectBuilder()
                .add("yummy", yummies)
                .build();
        return response.toString();
    }

}
