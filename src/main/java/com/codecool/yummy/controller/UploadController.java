package com.codecool.yummy.controller;

import com.codecool.yummy.model.User;
import com.codecool.yummy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class UploadController {

    private static final File recipeImagesDir = new File("recipeImages");
    private static final String recipeImagesDir_ABSOLUTE_PATH = recipeImagesDir.getAbsolutePath() + File.separator;
    private static final String FAILED_UPLOAD_MESSAGE = "Unsuccessful upload.";
    private static final String SUCCESS_UPLOAD_MESSAGE = "You successfully uploaded your picture.";

    @Autowired
    UserService userService;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("recipes", user.getFollowedRecipes());
        modelAndView.setViewName("home");
        if (file.isEmpty()) {
            modelAndView.addObject("message", String.format(FAILED_UPLOAD_MESSAGE, name, "file is empty"));
        } else {
            createRecipeImagesDirIfNeeded();
            modelAndView.addObject("success", createImage(name, file));
        }
        return modelAndView;
    }

    private void createRecipeImagesDirIfNeeded() {
        if (!recipeImagesDir.exists()) {
            recipeImagesDir.mkdirs();
        }
    }

    private String createImage(String name, MultipartFile file) {
        try {
            File image = new File(recipeImagesDir_ABSOLUTE_PATH + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image));
            stream.write(file.getBytes());
            stream.close();
            return String.format(SUCCESS_UPLOAD_MESSAGE, name);
        } catch (Exception e) {
            return String.format(FAILED_UPLOAD_MESSAGE, name, e.getMessage());
        }
    }

    @RequestMapping(value = "/image/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
        createRecipeImagesDirIfNeeded();
        File serverFile = new File("recipeImages/" + imageName + ".jpg");
        return Files.readAllBytes(serverFile.toPath());
    }
}
