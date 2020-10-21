package com.pdnguyen.cnnwebapp.Service;

import com.pdnguyen.cnnwebapp.Entity.User;
import com.pdnguyen.cnnwebapp.Repository.Custom.UserRepositoryCustom;
import com.pdnguyen.cnnwebapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Layer
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryCustom userCustomizedRepository;

    public List<User> getUserList() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

}
