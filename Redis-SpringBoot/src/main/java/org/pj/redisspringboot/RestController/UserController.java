package org.pj.redisspringboot.RestController;

import jakarta.validation.Valid;
import org.pj.redisspringboot.Model.Request.User;
import org.pj.redisspringboot.Model.Response.ApiResponse;
import org.pj.redisspringboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.save(user);
        return ResponseEntity.ok(new ApiResponse<>("User created successfully", createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>("User found", user));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Iterable<User>>> getAllUsers() {
        Iterable<User> users = userService.findAll();
        return ResponseEntity.ok(new ApiResponse<>("All users fetched", users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>("User deleted successfully", null));
    }
}
