package com.digital.art.service;


import com.digital.art.dto.UserDTO;
import com.digital.art.model.User;

import java.util.List;

public interface UserService {
    User save(UserDTO user);
    List<User> findAll();
    void delete(int id);
    User findOne(String username);
    User findById(int id);

}
