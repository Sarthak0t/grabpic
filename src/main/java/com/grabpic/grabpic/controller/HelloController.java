package com.grabpic.grabpic.controller;

import com.grabpic.grabpic.entity.User;
import com.grabpic.grabpic.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User saveduser=userService.saveUser(user);
        return ResponseEntity.status(201).body(saveduser);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> user_list(){
        List<User> res=userService.findUsers();  //Dependency injection used here once again to call the method from UserService.
        return ResponseEntity.ok(res);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User>getUser(@PathVariable long id){
        Optional<User> user = userService.getUser(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateuser(@PathVariable long id,@Valid @RequestBody User user){
        User updated=userService.updateuser(id,user);

        if(updated == null){
            return ResponseEntity.notFound().build();  //.build creates the ResponseEntity.
        }
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteuser(@PathVariable long id){
        boolean res=userService.deleteuser(id);
        if(res==true){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
