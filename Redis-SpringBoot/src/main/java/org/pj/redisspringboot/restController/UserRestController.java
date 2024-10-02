package org.pj.redisspringboot.restController;

import jakarta.validation.Valid;
import org.pj.redisspringboot.model.request.User;
import org.pj.redisspringboot.model.response.ApiResponse;
import org.pj.redisspringboot.service.UserServiceImpl;
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
public class UserRestController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserRestController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody User user) {
        User createdUser = userServiceImpl.save(user);
        return ResponseEntity.ok(new ApiResponse<>("User created successfully", createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable String id) {
        User user = userServiceImpl.findById(id);
        return ResponseEntity.ok(new ApiResponse<>("User found", user));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Iterable<User>>> getAllUsers() {
        Iterable<User> users = userServiceImpl.findAll();
        return ResponseEntity.ok(new ApiResponse<>("All users fetched", users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String id) {
        userServiceImpl.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>("User deleted successfully", null));
    }
}
