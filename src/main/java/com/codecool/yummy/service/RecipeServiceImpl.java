package com.codecool.yummy.service;

import com.codecool.yummy.model.Recipe;
import com.codecool.yummy.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by szilarddavid on 2017.07.17..
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    @Qualifier("recipeRepository")
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe findRecipeByName(String name) {
        return recipeRepository.findRecipeByName(name);
    }

    @Override
    public Recipe findRecipeById(Long id) {return recipeRepository.findRecipeById(id);}

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        recipe.setDate(new Date());
        recipe.setYummy(0);
        Recipe r = recipeRepository.save(recipe);
        return r;
    }
}
