package com.project.blog.servicesImpl;

import com.project.blog.exceptions.ResourceNotFound;
import com.project.blog.models.User;
import com.project.blog.repositories.UserRepository;
import com.project.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Long id) {
        User oldUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User", "Id", id)
        );
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setAbout(user.getAbout());
        return userRepository.save(oldUser);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User", "Id", id)
        );
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User", "Id", id)
        );
        userRepository.delete(user);
    }
}
