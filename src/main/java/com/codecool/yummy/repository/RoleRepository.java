package com.codecool.yummy.repository;

/**
 * Created by szilarddavid on 2017.07.11..
 */
import com.codecool.yummy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{

    Role findByRole(String role);

}
