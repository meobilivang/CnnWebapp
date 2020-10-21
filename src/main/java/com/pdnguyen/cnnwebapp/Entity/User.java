package com.pdnguyen.cnnwebapp.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user", catalog = "FtBVsRUeKh")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id")
    private Integer id;

    @Basic(optional = false)
    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    //@Basic(optional = false)
    @Column(name="user_name", nullable = false)
    private String user_name;

    @Basic(optional = false)
    @JsonIgnore
    @Column(name="password")
    private String password;

    @Basic(optional=false)
    @Column(name="status")
    private Integer status;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UserDepartment> userCollection;

    public User() {
    }

    public User(String user_name) {
        this.user_name = user_name;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String email, String user_name, String password, Integer status, Date createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.user_name = user_name;
        this.password = password;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(user_name, user.user_name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(status, user.status) &&
                Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, user_name, password, status, createdAt);
    }
}
