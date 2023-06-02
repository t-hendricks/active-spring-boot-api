package com.active.demo.service;

import com.active.demo.exception.InformationExistException;
import com.active.demo.model.User;
import com.active.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository/*, @Lazy PasswordEncoder passwordEncoder*/) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User userObj) {
        if(!userRepository.existsByUserName(userObj.getUserName())) {
//            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObj);
        } else {
            throw new InformationExistException("User with username: " + userObj.getUserName() + " exists");
        }
    }
}
