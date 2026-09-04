package com.monolithic.FoodOrdering.user.service;

import com.monolithic.FoodOrdering.user.exceptions.PasswordDoesntMatch;
import com.monolithic.FoodOrdering.user.exceptions.UserAlreadyExists;
import com.monolithic.FoodOrdering.user.exceptions.UserDoesnotExists;
import com.monolithic.FoodOrdering.user.model.User;
import com.monolithic.FoodOrdering.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;


    public User register(User input){
        if(userRepo.findByEmail(input.getEmail()).isPresent()){
            throw new UserAlreadyExists("Email:"+ input.getEmail() +" already Exists");
        }
        return userRepo.save(input);

    }

    public User login(User input){
        User user = userRepo.findByEmail(input.getEmail()).orElseThrow(()->new UserDoesnotExists("Email not found"));

        if(!user.getPassword().equals(input.getPassword())){
            throw new PasswordDoesntMatch("password doesnt match "+ input.getPassword());
        }
        return user;
    }
}
