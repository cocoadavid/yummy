package com.codecool.yummy.controller;

import com.codecool.yummy.model.Recipe;
import com.codecool.yummy.model.User;
import com.codecool.yummy.service.RecipeService;
import com.codecool.yummy.service.UserService;
import com.codecool.yummy.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by szilarddavid on 2017.07.11..
 */

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    StorageService storageService;

    List<String> files = new ArrayList<String>();

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

//    FIRST VERSION OF IMAGE UPLOADING
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public void handleFormUpload(
//            @RequestParam("file") MultipartFile file) throws IOException {
//        if (!file.isEmpty()) {
//            BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
//            File destination = new File("image_id.png"); // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
//            System.out.println(destination.exists());
//            ImageIO.write(src, "png", destination);
//            //Save the id you have used to create the file name in the DB. You can retrieve the image in future with the ID.
//        }
//    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            storageService.store(file);
            model.addAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
            files.add(file.getOriginalFilename());
        } catch (Exception e) {
            model.addAttribute("message", "FAIL to upload " + file.getOriginalFilename() + "!");
        }
        return "uploadForm";
    }

}
