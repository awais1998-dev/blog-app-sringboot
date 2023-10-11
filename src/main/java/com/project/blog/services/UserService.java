package com.project.blog.services;

import com.project.blog.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User createUser(User user);
    User updateUser(User user, Long id);
    User getUserById(Long id);
    void deleteUser(Long id);

}
