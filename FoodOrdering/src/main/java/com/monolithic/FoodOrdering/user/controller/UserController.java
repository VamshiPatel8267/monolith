package com.monolithic.FoodOrdering.user.controller;

import com.monolithic.FoodOrdering.orders.model.Order;
import com.monolithic.FoodOrdering.user.model.User;
import com.monolithic.FoodOrdering.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        User result = userService.register(user);
        return result;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login1(@RequestBody User user){
        User result = userService.login(user);
        return ResponseEntity.status(HttpStatus.OK).body("logged in "+ user.getEmail());
    }

    @GetMapping("/getAllUsers")
    public List<User> getUsers(){
        List<User> users = userService.getAllUsers();
        return users;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        String name = userService.deleteUserById(id);
        return name;
    }

}
