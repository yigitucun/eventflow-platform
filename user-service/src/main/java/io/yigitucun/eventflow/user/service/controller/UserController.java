package io.yigitucun.eventflow.user.service.controller;

import io.yigitucun.eventflow.user.service.dto.requests.CreateUserRequest;
import io.yigitucun.eventflow.user.service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request){
        userService.insertUser(request);
        return ResponseEntity.status(201).body(request);
    }

}
