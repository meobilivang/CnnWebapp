package com.pdnguyen.cnnwebapp.Entity.DTO;


import java.util.Date;
import java.util.Objects;

public class UserDetailIncomingDto {

    private int id;
    private String name;
    private String userName;
    private String password;
    private String role;
    private int roleId;
    private String email = "";
    private String department;
    private Date createdAt;

    public UserDetailIncomingDto() {
    }

    //Constructor for User
    public UserDetailIncomingDto(int id, String name, String userName, String role, int roleId, String department, Date createdAt) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.role = role;
        this.roleId = roleId;
        this.department = department;
        this.createdAt = createdAt;
    }

    public UserDetailIncomingDto(String name, String userName, String password, String role, int roleId, String email, String department, Date createdAt) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.roleId = roleId;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailIncomingDto that = (UserDetailIncomingDto) o;
        return id == that.id &&
                roleId == that.roleId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(role, that.role) &&
                Objects.equals(department, that.department) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, role, roleId, department, createdAt);
    }

    @Override
    public String toString() {
        return "UserDetailIncomingDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                ", roleId=" + roleId +
                ", department='" + department + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
