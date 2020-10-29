package com.pdnguyen.cnnwebapp.Service;

import com.pdnguyen.cnnwebapp.Entity.User;
import com.pdnguyen.cnnwebapp.Entity.UserDepartment;
import com.pdnguyen.cnnwebapp.Repository.Custom.UserDepartmentRepositoryCustom;
import com.pdnguyen.cnnwebapp.Repository.Custom.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

/***
 *
 * BUSINESS LAYER
 *
 *
 * Inherit UserDetailsServices of Spring Security
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

    @Autowired
    private UserDepartmentRepositoryCustom userDepartmentRepositoryCustom;

    /**
     *
     * AUTHENTICATION PROCESS
     * Authenticate a login attempt
     * Granting Authority to Login User
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User logInUser = userRepositoryCustom.findByUserName(userName);
        //Cant find the user
        if (logInUser == null)
            throw new UsernameNotFoundException("User Not Found !");

        //Authorization
        List<UserDepartment> userDepartmentList = userDepartmentRepositoryCustom.findUserById(logInUser);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if (userDepartmentList.size() > 0) {
            for (UserDepartment user : userDepartmentList) {
                //Setting the Role for logIn user
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoleId().getRoleName().toUpperCase()));
            }
        }

        return new org.springframework.security.core
                .userdetails.User(logInUser.getUser_name(), logInUser.getPassword(), grantedAuthorities);
    }

}
