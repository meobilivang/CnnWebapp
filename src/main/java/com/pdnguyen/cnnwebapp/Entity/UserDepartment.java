package com.pdnguyen.cnnwebapp.Entity;

import javax.persistence.*;
import java.util.Objects;


/**
 *
 * PERSISTENCE LAYER
 *
 * UserDepartment object representing user_department table in DB
 *
 */
@Entity
@Table(name = "user_department", catalog = "FtBVsRUeKh")
public class UserDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;

    //userId is foreign key from User table
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    //roleId is foreign key from school_role table
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SchoolRole roleId;

    @Column(name = "department")
    @Basic(optional = false)
    private String department;

    public UserDepartment() {
    }

    public UserDepartment(Integer id) {
        this.id = id;
    }

    public UserDepartment(Integer id, String userName, String department) {
        this.id = id;
        this.userName = userName;
        this.department = department;
    }

    public UserDepartment(Integer id, String userName, SchoolRole roleId, User userId, String department) {
        this.id = id;
        this.userName = userName;
        this.roleId = roleId;
        this.userId = userId;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SchoolRole getRoleId() {
        return roleId;
    }

    public void setRoleId(SchoolRole roleId) {
        this.roleId = roleId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDepartment that = (UserDepartment) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, roleId, userId, department);
    }

    @Override
    public String toString() {
        return "UserDepartment{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", roleId=" + roleId +
                ", userId=" + userId +
                ", department='" + department + '\'' +
                '}';
    }
}
