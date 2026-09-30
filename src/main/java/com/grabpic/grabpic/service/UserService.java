package com.grabpic.grabpic.service;

import com.grabpic.grabpic.entity.User;
import com.grabpic.grabpic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;     //Dependency Injection (UserRepository)
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public List<User> findUsers(){ //this method is used to findall users so no argument passed inside it
        return userRepository.findAll();
    }
    public User updateuser(long id, User user){
        Optional<User> existing_user=userRepository.findById(id);
        if(existing_user.isPresent()){
            User existing= existing_user.get();

            existing.setUsername(user.getUsername());
            existing.setEmail(user.getEmail());
            existing.setPassword(user.getPassword());
            return userRepository.save(existing);
        }
        return null;
    }
    public void deleteuser(long id){
        userRepository.deleteById(id);
    }
}
