package com.codecool.yummy.service;

import com.codecool.yummy.model.Recipe;

/**
 * Created by szilarddavid on 2017.07.17..
 */
public interface RecipeService {

    public Recipe findRecipeByName(String name);
    public Recipe findRecipeById(Long id);
    public Recipe saveRecipe(Recipe recipe);
}
