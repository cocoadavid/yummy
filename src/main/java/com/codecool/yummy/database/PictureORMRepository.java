package com.codecool.yummy.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by szilarddavid on 2017.07.06..
 */
public interface PictureORMRepository extends CrudRepository<PictureORMEntity, Long> {

    List<PictureORMEntity> findById(Long id);

}
