package com.grabpic.grabpic.controller;

import com.grabpic.grabpic.dto.LoginRequestDTO;
import com.grabpic.grabpic.dto.UserRequestDTO;
import com.grabpic.grabpic.dto.UserResponseDTO;
import com.grabpic.grabpic.entity.User;
import com.grabpic.grabpic.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){  //DTO conocept used here.
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());

        User saveduser=userService.saveUser(user);      // Convert request DTO to User entity, save it to the database,
                                                        // then convert the saved entity to a response DTO without exposing the password.

        UserResponseDTO responseDTO=new UserResponseDTO();
        responseDTO.setUsername(saveduser.getUsername());
        responseDTO.setEmail(saveduser.getEmail());
        responseDTO.setId(saveduser.getId());
        responseDTO.setCreatedAt(saveduser.getCreatedAt());

        return ResponseEntity.status(201).body(responseDTO);
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> user_list(){
        List<User> res=userService.findUsers();  //Dependency injection used here once again to call the method from UserService.
        List<UserResponseDTO> result=new ArrayList<>();
        for(User user: res){
            UserResponseDTO responseDTO=new UserResponseDTO();
            responseDTO.setUsername(user.getUsername());
            responseDTO.setId(user.getId());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setCreatedAt(user.getCreatedAt());
            result.add(responseDTO);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO>getUser(@PathVariable long id){
        Optional<User> user = userService.getUser(id);
        if(user.isPresent()){
            User user1=new User();   //changes here as well for DTOs.
            user1=user.get();
            UserResponseDTO responseDTO=new UserResponseDTO();
            responseDTO.setId(user1.getId());
            responseDTO.setEmail(user1.getEmail());
            responseDTO.setUsername(user1.getUsername());
            responseDTO.setCreatedAt(user1.getCreatedAt());
            return ResponseEntity.ok(responseDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateuser(@PathVariable long id,@Valid @RequestBody UserRequestDTO userRequestDTO){
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());

        User updated=userService.updateuser(id,user);

        if(updated == null){
            return ResponseEntity.notFound().build();  //.build creates the ResponseEntity.
        }
        UserResponseDTO responseDTO=new UserResponseDTO();
        responseDTO.setId(updated.getId());
        responseDTO.setEmail(updated.getEmail());
        responseDTO.setUsername(updated.getUsername());
        responseDTO.setCreatedAt(updated.getCreatedAt());

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/users/login")
    public ResponseEntity<Void> logincredentials(@Valid @RequestBody LoginRequestDTO loginRequestDTO){

        User user=new User();
        user.setEmail(loginRequestDTO.getEmail());
        user.setPassword(loginRequestDTO.getPassword());
        boolean validate=userService.checklogin(user);
        if(!validate){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
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
