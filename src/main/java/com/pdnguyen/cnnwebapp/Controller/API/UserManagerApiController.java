package com.pdnguyen.cnnwebapp.Controller.API;

import com.pdnguyen.cnnwebapp.Entity.DTO.UserDetailIncomingDto;
import com.pdnguyen.cnnwebapp.Entity.DTO.UserDetailListIncomingDto;
import com.pdnguyen.cnnwebapp.Entity.SchoolRole;
import com.pdnguyen.cnnwebapp.Entity.User;
import com.pdnguyen.cnnwebapp.Service.SchoolRoleService;
import com.pdnguyen.cnnwebapp.Service.UserManagerService;
import com.pdnguyen.cnnwebapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * API for User Management actions
 *
 */
@RequestMapping("api/user-manager")
@RestController
public class UserManagerApiController {

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private UserService userService;

    @Autowired
    private SchoolRoleService schoolRoleService;

    /**
     *
     * API retrieve full details of all users
     * Only ROLE_PROF can view
     * @return
     */
    @GetMapping(path = "/user-list-full")
    public ResponseEntity<List<User>> getUserDetailList() {
        List<User> userList = userService.getUserList();
        return ResponseEntity.ok(userList);
    }

    /**
     * API retrieve list of users
     * @return
     */
    @GetMapping(path = "/user-list")
    public ResponseEntity<List<UserDetailListIncomingDto>> getUserList() {
        List<UserDetailListIncomingDto> userList =  userManagerService.getUserList();
        return ResponseEntity.ok(userList);
    }

    /**
     * API retrieve list of roles
     * @return
     */
    @GetMapping(path = "/role-list")
    public ResponseEntity<List<SchoolRole>> getRoleList() {
        List<SchoolRole> schoolRoles = schoolRoleService.getRoleList();
        return ResponseEntity.ok(schoolRoles);
    }

    /**
     * API retrieve user by id
     * @param userId
     * @return
     */
    @GetMapping(value = "/get-user/{userId}")
    public ResponseEntity<UserDetailIncomingDto> getUser(@PathVariable int userId) {
        return ResponseEntity.ok(userManagerService.getUserById(userId));
    }

    /**
     * API add new user
     * @param userDetailIncomingDto
     */
    @PostMapping(path="/add-user")
    public void addUser(@RequestBody UserDetailIncomingDto userDetailIncomingDto) {
        try {
            userManagerService.addUser(userDetailIncomingDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * API edit user
     * @param userDetailIncomingDto
     */
    @PutMapping(value="/edit-user")
    public void editUser(@RequestBody UserDetailIncomingDto userDetailIncomingDto) {
        try {
            userManagerService.editUser(userDetailIncomingDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * API change password
     * @param userDetailIncomingDto
     * @return
     */
    @PutMapping(value="/change-password")
    public ResponseEntity changePassword(@RequestBody UserDetailIncomingDto userDetailIncomingDto) {
        Integer val = userManagerService.changePassword(userDetailIncomingDto);

        return val == 1
                ? ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Password Changed !")
                : ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No Found");
    }

    /**
     * API delete user by id
     * @param userId
     */
    @DeleteMapping(path="/delete-user/{userId}")
    public void deleteUser(@PathVariable int userId) {
        try {
            userManagerService.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
