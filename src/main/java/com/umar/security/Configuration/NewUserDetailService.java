package com.umar.security.Configuration;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.umar.security.UserPrinciple;
import com.umar.security.Entity.User;
import com.umar.security.Repository.UserRepo;

@Service
public class NewUserDetailService implements UserDetailsService{

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        
        List<User> users= userRepo.findByname(username);

        if (null == users) {
            throw new UsernameNotFoundException("User with "+username+" not Found");
        }

        UserPrinciple  userPrinciple = new UserPrinciple(users.get(0));

        return userPrinciple;
    }
}
