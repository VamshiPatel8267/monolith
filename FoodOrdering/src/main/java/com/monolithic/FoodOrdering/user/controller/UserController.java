package com.monolithic.FoodOrdering.user.controller;

import com.monolithic.FoodOrdering.user.model.User;
import com.monolithic.FoodOrdering.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        User result = userService.register(user);
        return result;
    }

//    @PostMapping("/login")
//    public User login(@RequestBody User user){
//        User result = userService.login(user);
//        return result;
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login1(@RequestBody User user){
        User result = userService.login(user);
        return ResponseEntity.status(HttpStatus.OK).body("logged in "+ user.getEmail());
    }
}
