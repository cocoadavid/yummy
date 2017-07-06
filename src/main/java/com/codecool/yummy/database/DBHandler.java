package com.codecool.yummy.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by szilarddavid on 2017.07.06..
 */
@Repository
public class DBHandler {

    private UserORMRepository userRepository;
    private RecipeORMRepository recipeRepository;
    private PictureORMRepository pictureRepository;
    private CommentORMRepository commentRepository;

    @Autowired
    public DBHandler(UserORMRepository userRepository, RecipeORMRepository recipeRepository,
                     PictureORMRepository pictureRepository, CommentORMRepository commentRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.pictureRepository = pictureRepository;
        this.commentRepository = commentRepository;
    }

    public void deleteAllRepos() {
        userRepository.deleteAll();
        recipeRepository.deleteAll();
        pictureRepository.deleteAll();
        commentRepository.deleteAll();
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }

    public void deleteRecipes() {
        recipeRepository.deleteAll();
    }

    public void deletePictures() {
        pictureRepository.deleteAll();
    }

    public void deleteComments() {
        commentRepository.deleteAll();
    }



}
