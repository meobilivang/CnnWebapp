package com.pdnguyen.cnnwebapp.Service;

import com.pdnguyen.cnnwebapp.Entity.DTO.UserDetailIncomingDto;
import com.pdnguyen.cnnwebapp.Entity.DTO.UserDetailListIncomingDto;
import com.pdnguyen.cnnwebapp.Entity.SchoolRole;
import com.pdnguyen.cnnwebapp.Entity.User;
import com.pdnguyen.cnnwebapp.Entity.UserDepartment;
import com.pdnguyen.cnnwebapp.Repository.Custom.UserDepartmentRepositoryCustom;
import com.pdnguyen.cnnwebapp.Repository.Custom.UserRepositoryCustom;
import com.pdnguyen.cnnwebapp.Repository.UserDepartmentRepository;
import com.pdnguyen.cnnwebapp.Repository.UserRepository;
import com.pdnguyen.cnnwebapp.Utils.PasswordEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.Exception;

/**
 *
 * Accessing User details
 *
 */
@Service
public class UserManagerService {

    @Autowired
    private UserDepartmentRepository userDepartmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDepartmentRepositoryCustom userDepartmentRepositoryCustom;

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

    @Autowired
    private PasswordEncryptionService passwordEncryptionService;

    /***
     *
     * @return
     */
    public List<UserDetailListIncomingDto> getUserList() {
        List<UserDetailListIncomingDto> userList = new ArrayList<>();
        List<UserDepartment> userDepartments = new ArrayList<>();
        userDepartments =  userDepartmentRepository.findAll();
        userDepartments.forEach(eachUser -> userList.add(new UserDetailListIncomingDto(
                eachUser.getUserId().getId(),
                eachUser.getUserName(),
                eachUser.getRoleId().getRoleName(),
                eachUser.getRoleId().getId(),
                eachUser.getDepartment(),
                eachUser.getUserId().getCreatedAt())
        ));
        return userList;
    }

    /***
     *
     * @param userId
     * @return
     */
    public UserDetailIncomingDto getUserById(int userId) {
        UserDetailIncomingDto userDetailIncomingDto = new UserDetailIncomingDto();
        List<UserDepartment> userList = userDepartmentRepositoryCustom.findUserById(new User(userId));
        UserDepartment userDepartment = userList.get(0);

        if (userDepartment != null) {
            userDetailIncomingDto.setId(userDepartment.getUserId().getId());
            userDetailIncomingDto.setName(userDepartment.getUserId().getName());
            userDetailIncomingDto.setUserName(userDepartment.getUserName());
            userDetailIncomingDto.setRoleId(userDepartment.getRoleId().getId());
            userDetailIncomingDto.setRole(userDepartment.getRoleId().getRoleName());
            userDetailIncomingDto.setEmail(userDepartment.getUserId().getEmail());
            userDetailIncomingDto.setDepartment(userDepartment.getDepartment());
            userDetailIncomingDto.setCreatedAt(userDepartment.getUserId().getCreatedAt());
        }

        return userDetailIncomingDto ;
    }

    /**
     *
     * @param userName
     * @return
     */
    public UserDetailIncomingDto getUserByUserName (String userName) {
        UserDetailIncomingDto userDetailIncomingDto = new UserDetailIncomingDto();
        List<UserDepartment> userList = userDepartmentRepositoryCustom.findUserByUsername(new User(userName));
        UserDepartment userDepartment = userList.get(0);

        if (userDepartment != null) {
            userDetailIncomingDto.setId(userDepartment.getUserId().getId());
            userDetailIncomingDto.setName(userDepartment.getUserId().getName());
            userDetailIncomingDto.setUserName(userDepartment.getUserName());
            userDetailIncomingDto.setRoleId(userDepartment.getRoleId().getId());
            userDetailIncomingDto.setRole(userDepartment.getRoleId().getRoleName());
            userDetailIncomingDto.setEmail(userDepartment.getUserId().getEmail());
            userDetailIncomingDto.setDepartment(userDepartment.getDepartment());
            userDetailIncomingDto.setCreatedAt(userDepartment.getUserId().getCreatedAt());
        }

        return userDetailIncomingDto;
    }

    /**
     *
     * @param userDetailIncomingDto
     */
    public void addUser(UserDetailIncomingDto userDetailIncomingDto) {
        //Creating 2 Entities: User and UserDetail to INSERT into database
        try {
            //New User
            User newUser = new User();
            newUser.setName(userDetailIncomingDto.getName());
            newUser.setEmail(userDetailIncomingDto.getEmail());
            newUser.setUser_name(userDetailIncomingDto.getUserName());
            newUser.setPassword(passwordEncryptionService.encryptPassword(userDetailIncomingDto.getPassword()));
            newUser.setStatus(1);
            newUser.setCreatedAt(new Date());
            userRepository.save(newUser);

            //New UserDeparment Object
            UserDepartment newUserDepartment = new UserDepartment();
            newUserDepartment.setUserId(userRepository.getOne(newUser.getId()));
            newUserDepartment.setUserName(newUser.getUser_name());
            newUserDepartment.setRoleId(new SchoolRole(userDetailIncomingDto.getRoleId()));
            newUserDepartment.setDepartment(userDetailIncomingDto.getDepartment());
            userDepartmentRepository.save(newUserDepartment);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     *
     * @param userDetailIncomingDto
     */
    public void editUser(UserDetailIncomingDto userDetailIncomingDto) {
        try {
            //Retrieve existing user. findById(id): using primary key of table
            //set this to null if cant find
            User editUser = userRepository.getOne(userDetailIncomingDto.getId());
            if (editUser.getName() != null) {
                editUser.setUser_name(userDetailIncomingDto.getUserName());
                if (userDetailIncomingDto.getName() != null)
                    editUser.setName(userDetailIncomingDto.getName());
                if (userDetailIncomingDto.getEmail() != null)
                    editUser.setEmail(userDetailIncomingDto.getEmail());
                userRepository.save(editUser);

                //Create new UserDepartment Entity
                UserDepartment userDepartment = userDepartmentRepositoryCustom.findUserById(editUser).get(0);
                userDepartment.setUserName(editUser.getUser_name());
                userDepartment.setUserId(editUser);
                userDepartment.setRoleId(new SchoolRole(userDetailIncomingDto.getRoleId()));
                userDepartment.setDepartment(userDetailIncomingDto.getDepartment());
                userDepartmentRepository.save(userDepartment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param userId
     * @return
     */
    public void deleteUser(int userId) {
        try {
//            User deleteUser = userRepository.findById(userId)
//                    .orElse(null);
            User deleteUser = userRepository.getOne(userId);
            if (deleteUser != null) {
                List<UserDepartment> foundUser = userDepartmentRepositoryCustom.findUserById(deleteUser);
                for (UserDepartment singleUserDepartment : foundUser) {
                    userDepartmentRepository.delete(singleUserDepartment);
                }
                userRepository.delete(deleteUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Naive implementation of Password change functionality

     * @param userDetailIncomingDto
     * @return
     */
    public Integer changePassword(UserDetailIncomingDto userDetailIncomingDto) {
        User newPasswordUser = userRepositoryCustom.findByUserName(userDetailIncomingDto.getUserName());
        String newPassword = userDetailIncomingDto.getPassword();
        //Cant find User
        if (newPasswordUser == null)
            return 0;
        //Change password
        newPasswordUser.setPassword(passwordEncryptionService.encryptPassword(newPassword));
        userRepository.save(newPasswordUser);

        return 1;
    }
}
