package com.picopost.back.controller;

import com.picopost.back.model.User;
import com.picopost.back.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
// @RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    // Get a user by id
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") final Long id) {
      Optional<User> user = userService.getUser(id);
      if (user.isPresent()) {
        return ResponseEntity.ok(user.get());
      } else {
        return ResponseEntity.notFound().build();
      }
    }

    // Create a new user
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        User newUser = userService.saveUser(user);
        return ResponseEntity.created(new URI("/api/users/" + newUser.getId())).body(newUser);
    }

    // Update a user
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    // Delete a user
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
