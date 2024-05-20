package com.ssjvirtually.springsecuritydemo.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssjvirtually.springsecuritydemo.entity.User;
import com.ssjvirtually.springsecuritydemo.service.UserService;

import java.util.Optional;


@RestController
public class LoginController {
    

    UserService userService;


    BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

        /**
     * Sign up a new user by encoding the provided password and saving the user to the database.
     *
     * @param  user  the user object containing the user's information
     * @return       the ID of the newly created user
     */
    @PostMapping("/sign-up")
    public Long signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userService.saveUser(user).getId();
    }
    
   
    

}
