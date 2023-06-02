package com.active.demo.controller;

import com.active.demo.exception.InformationExistException;
import com.active.demo.model.User;
import com.active.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/auth")
public class UserController {
    private UserService userService;
    static HashMap<String , Object> message = new HashMap<>();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User userObj) {
        try {
            User newUser = userService.createUser(userObj);
            message.put("message","success");
            message.put("data",newUser);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (InformationExistException e){
            message.put("message",e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}
