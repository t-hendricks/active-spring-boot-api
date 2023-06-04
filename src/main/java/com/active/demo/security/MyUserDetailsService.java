package com.active.demo.security;

import com.active.demo.model.User;
import com.active.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService is used to load user-specific data.
 * The UserDetailsService is a core interface in Spring
 * Security framework, which is used to retrieve the user’s
 * authentication and authorization information.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserService userService;

    // setter
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method is used to find the given username inside
     * the database.
     *
     * @param userName is a string used check for credentials
     * @return UserDetails is an object provided by Spring framework security
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(userName);
        return new MyUserDetails(user);
    }
}
