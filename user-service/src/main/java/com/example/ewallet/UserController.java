package com.example.ewallet;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public User createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        User user = User.builder()
                .username(userCreateRequest.getUsername())
                .email(userCreateRequest.getEmail())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                .contact(userCreateRequest.getContact())
                .build();
        return userService.createUser(user);
    }

    @GetMapping("/user")
    public User getUser(@RequestParam("id") int id){
        return userService.getUser(id);
    }
}
