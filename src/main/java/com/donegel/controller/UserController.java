package com.donegel.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donegel.exception.ResourceNotFoundException;
import com.donegel.domain.User;
import com.donegel.repository.UserRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // http://localhost:8080/api/v1/users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // http://localhost:8080/api/v1/users/1
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
        return ResponseEntity.ok().body(user);
    }
    // http://localhost:8080/api/v1/users
    @PostMapping("/save")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    // http://localhost:8080/api/v1/users/1
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long userId,
            @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));

        user.setEmailId(userDetails.getEmailId());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        user.setUpdatedAt(new Date());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // http://localhost:8080/api/v1/users/1
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteUser(
            @PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
