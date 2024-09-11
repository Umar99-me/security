package com.umar.security.Service;

import java.util.List;

import com.umar.security.Entity.User;

public interface UsersService {

    public User registerUser(User user);
    public List<User> findAllUsers();
    public String login(User user);
}
