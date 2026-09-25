package com.grabpic.grabpic.controller;

import com.grabpic.grabpic.entity.User;
import com.grabpic.grabpic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello(){
        return "My workspace";
    }
    private final UserService userService;   //Dependency Injection (UserService)
    @Autowired
    public HelloController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
