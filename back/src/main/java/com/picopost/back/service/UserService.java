package com.picopost.back.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picopost.back.model.User;
import com.picopost.back.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Optional<User> getUser(final Long userId) {
    return userRepository.findById(userId);
  }

  public Iterable<User> getUsers() {
    return userRepository.findAll();
  }

  public void deleteUser(final Long userId) {
    userRepository.deleteById(userId);
  }

  public User saveUser(User user) {
    User savedUser = userRepository.save(user);
    return savedUser;
  }

  public User updateUser(final Long userId, User newUser) {
    return userRepository.findById(userId)
        .map(user -> {
          if (newUser.getUsername() != null) {
            user.setUsername(newUser.getUsername());
          }
          if (newUser.getEmail() != null) {
            user.setEmail(newUser.getEmail());
          }
          if (newUser.getPassword() != null) {
            user.setPassword(newUser.getPassword());
          }
          if (newUser.getPosts() != null) {
            user.setPosts(newUser.getPosts());
          }
          return userRepository.save(user);
        }).orElseGet(() -> {
          newUser.setId(userId);
          return userRepository.save(newUser);
        });
  }
}
