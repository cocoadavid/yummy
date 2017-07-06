package com.codecool.yummy.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by szilarddavid on 2017.07.06..
 */
public interface UserORMRepository extends CrudRepository<UserORMEntity, Long> {

    List<UserORMEntity> findByName(String name);
}
