package com.codecool.yummy.repository;

/**
 * Created by szilarddavid on 2017.07.11..
 */
import com.codecool.yummy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
