package com.cyfire.users;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Steven Marshall Sheets
 */
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.findAll();
    }
}
