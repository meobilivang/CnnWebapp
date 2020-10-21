package com.pdnguyen.cnnwebapp.Controller.API;

import java.util.List;

import com.pdnguyen.cnnwebapp.Entity.DTO.UserDetailListIncomingDto;
import com.pdnguyen.cnnwebapp.Entity.SchoolRole;
import com.pdnguyen.cnnwebapp.Service.SchoolRoleService;
import com.pdnguyen.cnnwebapp.Service.UserManagerService;
import com.pdnguyen.cnnwebapp.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 */


@RequestMapping("api/user")
@RestController
public class BasicUserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private SchoolRoleService schoolRoleService;

    @Autowired
    private UserManagerService userManagerService;

    @GetMapping(path = "/user-list")
    public ResponseEntity<List<UserDetailListIncomingDto>> getUserList() {
        List<UserDetailListIncomingDto> userList =  userManagerService.getUserList();
        return ResponseEntity.ok(userList);
    }

    @GetMapping(value = "/role-list")
    public ResponseEntity<List<SchoolRole>> getRoleList() {
        List<SchoolRole> schoolRoleList = schoolRoleService.getRoleList();
        return ResponseEntity.ok(schoolRoleList);
    }

}
