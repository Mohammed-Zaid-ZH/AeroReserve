package com.aeroreserve.controller;

import com.aeroreserve.model.User;
import com.aeroreserve.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }

    @PostMapping("/register")
    public User register(@RequestBody User user) { return service.save(user); }

    @GetMapping
    public List<User> getAll() { return service.getAll(); }
}
