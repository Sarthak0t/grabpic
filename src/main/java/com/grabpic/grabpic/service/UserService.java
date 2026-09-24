package com.grabpic.grabpic.service;

import com.grabpic.grabpic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;     //Dependency Injection (UserRepository)
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
}
