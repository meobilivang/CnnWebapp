package com.pdnguyen.cnnwebapp.Entity.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class UserDetailListIncomingDto {
    private int id;
    private String userName;
    private String role;
    private int roleId;
    private String department;
    private Date createdAt;

    public UserDetailListIncomingDto(int id, String userName, String role, int roleId, String department, Date createdAt) {
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.roleId = roleId;
        this.department = department;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
