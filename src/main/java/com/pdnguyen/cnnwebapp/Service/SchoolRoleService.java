package com.pdnguyen.cnnwebapp.Service;

import com.pdnguyen.cnnwebapp.Entity.SchoolRole;
import com.pdnguyen.cnnwebapp.Repository.SchoolRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolRoleService {
    @Autowired
    private SchoolRoleRepository schoolRoleRepository;

    public List<SchoolRole> getRoleList() {
        List<SchoolRole> roleList = schoolRoleRepository.findAll();
        return roleList;
    }

}
