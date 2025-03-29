package com.example.exam.Controller;

import com.example.exam.Entity.User;
import com.example.exam.Service.UserService;
import com.example.exam.utils.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // User login
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User user) {
        try {
            User authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
            return authenticatedUser != null
                    ? ResponseHandler.generateResponse("Login successful", HttpStatus.OK, authenticatedUser)
                    : ResponseHandler.generateErrorResponse("Invalid credentials", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            logger.error("Error during login", e);
            return ResponseHandler.generateErrorResponse("Login failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseHandler.generateResponse("User registered successfully", HttpStatus.CREATED, registeredUser);
        } catch (Exception e) {
            logger.error("Error registering user", e);
            return ResponseHandler.generateErrorResponse("Registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return user != null
                    ? ResponseHandler.generateResponse("User found", HttpStatus.OK, user)
                    : ResponseHandler.generateNotFoundResponse("User not found");
        } catch (Exception e) {
            logger.error("Error retrieving user with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to retrieve user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update user
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return updatedUser != null
                    ? ResponseHandler.generateResponse("User updated successfully", HttpStatus.OK, updatedUser)
                    : ResponseHandler.generateNotFoundResponse("User not found");
        } catch (Exception e) {
            logger.error("Error updating user with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        try {
            boolean deleted = userService.deleteUserById(id);
            return deleted
                    ? ResponseHandler.generateResponse("User deleted successfully", HttpStatus.NO_CONTENT, null)
                    : ResponseHandler.generateNotFoundResponse("User not found");
        } catch (Exception e) {
            logger.error("Error deleting user with ID: " + id, e);
            return ResponseHandler.generateErrorResponse("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
