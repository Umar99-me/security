package com.umar.security.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umar.security.Entity.User;
import com.umar.security.Service.UsersService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UsersService usersService;
    @PostMapping("/register")
    public User registUser(@RequestBody User user){
        return usersService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return usersService.login(user);
    }
}
