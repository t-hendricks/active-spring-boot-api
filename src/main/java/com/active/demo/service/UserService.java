package com.active.demo.service;

import com.active.demo.exception.InformationExistException;
import com.active.demo.exception.InformationNotFoundException;
import com.active.demo.model.User;
import com.active.demo.model.request.LoginRequest;
import com.active.demo.model.response.LoginResponse;
import com.active.demo.repository.UserRepository;
import com.active.demo.security.JWTUtils;
import com.active.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JWTUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;

    // constructor
    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils,
                       @Lazy AuthenticationManager authenticationManager,
                       @Lazy MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
    }

    /**
     * This method checks for an existing username. If it's not found, then the
     * provided credentials will be saved in the database.
     *
     * @param userObj This is the unique credentials used to register
     * @return User {object} This is the provided credentials
     */
    public User createUser(User userObj) {
        if(!userRepository.existsByUserName(userObj.getUserName())) {
            userObj.setPassword(passwordEncoder.encode(userObj.getPassword()));
            return userRepository.save(userObj);
        } else {
            throw new InformationExistException("User with username: " + userObj.getUserName() + " exists");
        }
    }

    /**
     * This method searches database by the given username.
     *
     * @param userName String This is used to find an existing user
     * @return User {object} This is the provided credentials
     */
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    /**
     * This method looks for an existing user with the provided credentials,
     * then generates JWT and login session for the user to access private endpoints.
     *
     * @param loginRequest {object} This is given credentials
     * @return ResponseEntity
     */
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            myUserDetails = (MyUserDetails) authentication.getPrincipal();

            final String JWT = jwtUtils.generateJwtToken(myUserDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            throw new InformationNotFoundException("Username or Password is incorrect.");
        }
    }
}
