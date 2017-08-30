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
import org.springframework.web.bind.annotation.*;
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

//    private static final File recipeImagesDir = new File("recipeImages");
//    private static final String recipeImagesDir_ABSOLUTE_PATH = recipeImagesDir.getAbsolutePath() + File.separator;
//    private static final String FAILED_UPLOAD_MESSAGE = "You failed to upload [%s] because the file because %s";
//    private static final String SUCCESS_UPLOAD_MESSAGE = "You successfully uploaded file = [%s]";
//
//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    public ModelAndView uploadFileHandler(@RequestParam("name") String name,
//                                          @RequestParam("file") MultipartFile file) {
//        ModelAndView modelAndView = new ModelAndView("uploadForm");
//
//        if (file.isEmpty()) {
//            modelAndView.addObject("message", String.format(FAILED_UPLOAD_MESSAGE, name, "file is empty"));
//        } else {
//            createRecipeImagesDirIfNeeded();
//            modelAndView.addObject("message", createImage(name, file));
//        }
//
//        return modelAndView;
//    }
//
//    private void createRecipeImagesDirIfNeeded() {
//        if (!recipeImagesDir.exists()) {
//            recipeImagesDir.mkdirs();
//        }
//    }
//
//    private String createImage(String name, MultipartFile file) {
//        try {
//            File image = new File(recipeImagesDir_ABSOLUTE_PATH + name);
//            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image));
//            stream.write(file.getBytes());
//            stream.close();
//
//            return String.format(SUCCESS_UPLOAD_MESSAGE, name);
//        } catch (Exception e) {
//            return String.format(FAILED_UPLOAD_MESSAGE, name, e.getMessage());
//        }
//    }
//
//    @RequestMapping(value = "/image/{imageName}")
//    @ResponseBody
//    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
//        createRecipeImagesDirIfNeeded();
//        File serverFile = new File("recipeImages/" + imageName + ".jpg");
//
//        return Files.readAllBytes(serverFile.toPath());
//    }

}
