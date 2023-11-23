package com.digital.art.jpa;

import com.digital.art.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String userName);
    User findByUserId(int id);

}
