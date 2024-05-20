package com.ssjvirtually.springsecuritydemo.config;

import com.ssjvirtually.springsecuritydemo.entity.User;
import com.ssjvirtually.springsecuritydemo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepo.findByUsername(username);
        return byUsername.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    }
}
