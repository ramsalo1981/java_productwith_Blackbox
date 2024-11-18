package com.ramis.ramiproduct.Controller;

import com.ramis.ramiproduct.entity.User;
import com.ramis.ramiproduct.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.findById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public User createUser (@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser (@PathVariable int id, @RequestBody User userDetails) {
        User updatedUser  = userService.update(id, userDetails);
        return updatedUser  != null ? ResponseEntity.ok(updatedUser ) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Search by first name
    @GetMapping("/search/first-name")
    public List<User> searchByFirstName(@RequestParam String firstName) {
        return userService.searchByFirstName(firstName);
    }

    // Search by last name
    @GetMapping("/search/last-name")
    public List<User> searchByLastName(@RequestParam String lastName) {
        return userService.searchByLastName(lastName);
    }

    // Search by email
    @GetMapping("/search/email")
    public List<User> searchByEmail(@RequestParam String email) {
        return userService.searchByEmail(email);
    }

    // Create a user by email
    @PostMapping("/email")
    public ResponseEntity<User> createUserByEmail(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(409).body(null); // Conflict if user already exists
        }
        User createdUser  = userService.save(user);
        return ResponseEntity.status(201).body(createdUser );
    }

    // Get a user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // Update a user by email
    @PutMapping("/email/{email}")
    public ResponseEntity<User> updateUserByEmail(@PathVariable String email, @RequestBody User userDetails) {
        User existingUser  = userService.findByEmail(email);
        if (existingUser  != null) {
            existingUser .setFirstName(userDetails.getFirstName());
            existingUser .setLastName(userDetails.getLastName());
            existingUser .setPassword(userDetails.getPassword()); // Consider hashing the password
            User updatedUser  = userService.save(existingUser );
            return ResponseEntity.ok(updatedUser );
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a user by email
    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            userService.deleteById(user.getId()); // Assuming you have a method to delete by ID
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
