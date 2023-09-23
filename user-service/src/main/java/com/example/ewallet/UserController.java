package com.example.ewallet;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) throws JsonProcessingException {
        return userService.createUser(userCreateRequest.to());
    }

    @GetMapping("/user")
    public User getUser(@RequestParam("id") int id){
        return userService.getUser(id);
    }
}
