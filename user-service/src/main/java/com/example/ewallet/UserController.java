package com.example.ewallet;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public User createUser(@Valid @RequestBody UserCreateRequest userCreateRequest){
        return userService.createUser(userCreateRequest.to());
    }

    @GetMapping("/user")
    public User getUser(@RequestParam("id") int id){
        return userService.getUser(id);
    }
}
