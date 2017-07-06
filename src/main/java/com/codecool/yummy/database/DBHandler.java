package com.codecool.yummy.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public void saveAll(UserORMEntity user, RecipeORMEntity recipe, PictureORMEntity picture, CommentORMEntity comment) {
        userRepository.save(user);
        recipeRepository.save(recipe);
        pictureRepository.save(picture);
        commentRepository.save(comment);
    }

    public void saveUser(UserORMEntity user) {
        userRepository.save(user);
    }

    public void saveRecipe(RecipeORMEntity recipe) {
        recipeRepository.save(recipe);
    }

    public void savePicture(PictureORMEntity picture) {
        pictureRepository.save(picture);
    }

    public void saveComment(CommentORMEntity comment) {
        commentRepository.save(comment);
    }

    public List<UserORMEntity> findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<RecipeORMEntity> findRecipeByName(String name) {
        return recipeRepository.findRecipeByName(name);
    }

    public List<RecipeORMEntity> findByCategory(String category) {
        return recipeRepository.findByCategory(category);
    }

    public List<RecipeORMEntity> findByUser(UserORMEntity user) {
        return recipeRepository.findByUser(user);
    }

    public List<PictureORMEntity> findById(Long id) {
        return pictureRepository.findById(id);
    }

    public List<CommentORMEntity> findCommentByUser(UserORMEntity user) {
        return commentRepository.findCommentByUser(user);
    }



}
