package com.codecool.yummy.repository;

/**
 * Created by szilarddavid on 2017.07.11..
 */
import com.codecool.yummy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);
    List<User> findByUsernameContaining(String username);
}
