package com.ramis.ramiproduct.service;

import com.ramis.ramiproduct.Repository.UserRepository;
import com.ramis.ramiproduct.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(int id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword()); // Consider hashing the password
            return userRepository.save(user);
        }
        return null; // or throw an exception
    }

    // Search by first name
    public List<User> searchByFirstName(String firstName) {
        return userRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    // Search by last name
    public List<User> searchByLastName(String lastName) {
        return userRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    // Search by email
    public List<User> searchByEmail(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    // Additional methods for user management can be added here
}
