package com.grabpic.grabpic.service;

import com.grabpic.grabpic.config.PasswordConfig;
import com.grabpic.grabpic.entity.User;
import com.grabpic.grabpic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;//Dependency Injection (UserRepository)
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        return userRepository.save(user);
    }

    public List<User> findUsers() { //this method is used to findall users so no argument passed inside it
        return userRepository.findAll();
    }

    public User updateuser(long id, User user) {
        Optional<User> existing_user = userRepository.findById(id);
        if (existing_user.isPresent()) {
            User existing = existing_user.get();  //to get the user from optional
            String encode = passwordEncoder.encode(user.getPassword());  //hashing introduced.
            existing.setUsername(user.getUsername());
            existing.setEmail(user.getEmail());
            existing.setPassword(encode);
            return userRepository.save(existing);
        }
        return null;
    }

    public boolean deleteuser(long id) {
        Optional<User> id_valid = userRepository.findById(id);
        if (id_valid.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public boolean checklogin(User user) {
        Optional<User> existing_user = userRepository.findByEmail(user.getEmail());
        if (existing_user.isEmpty()) {
            return false;
        }
        User found_user = existing_user.get();
        return passwordEncoder.matches(user.getPassword(), found_user.getPassword());
    }
}