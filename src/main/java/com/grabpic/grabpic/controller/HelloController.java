package com.grabpic.grabpic.controller;

import com.grabpic.grabpic.entity.User;
import com.grabpic.grabpic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello(){
        return "My workspace";
    }
    private final UserService userService;
    @Autowired
    public HelloController(UserService userService){  //Dependency Injection (UserService)
        this.userService=userService;
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User saveduser=userService.saveUser(user);
        return ResponseEntity.status(201).body(saveduser);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> user_list(){
        List<User> res=userService.findUsers();  //Dependency injection used here once again to call the method from UserService.
        return ResponseEntity.ok(res);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateuser(@PathVariable long id, @RequestBody User user){
        User updated=userService.updateuser(id,user);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteuser(@PathVariable long id){
        userService.deleteuser(id);
        return ResponseEntity.noContent().build();
    }
}
