package com.codecool.yummy.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by szilarddavid on 2017.07.06..
 */
public interface CommentORMRepository extends CrudRepository<CommentORMEntity, Long> {

    List<CommentORMEntity> findByUser(UserORMEntity user);

}
