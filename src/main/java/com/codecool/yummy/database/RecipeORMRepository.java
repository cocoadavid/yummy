package com.codecool.yummy.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by szilarddavid on 2017.07.06..
 */
public interface RecipeORMRepository extends CrudRepository<RecipeORMEntity, Long> {

    List<RecipeORMEntity> findRecipeByName(String name);
    List<RecipeORMEntity> findByCategory(String category);
    List<RecipeORMEntity> findByUser(UserORMEntity user);
}
