package com.codecool.yummy.repository;

import com.codecool.yummy.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by szilarddavid on 2017.07.17..
 */

@Repository("recipeRepository")
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findRecipeByName(String name);
}
