package com.pdnguyen.cnnwebapp.Entity;

import javax.persistence.*;

import java.util.Collection;
import java.util.Objects;

/**
 *
 * PERSISTENCE LAYER
 *
 * SchoolRole object representing School_role table in DB
 *
 */
@Entity
@Table(name = "school_role", catalog = "FtBVsRUeKh")
public class SchoolRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "role_name")
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<UserDepartment> SchoolRoleCollection;

    public SchoolRole() {
    }

    public SchoolRole(Integer id) {
        this.id = id;
    }

    public SchoolRole(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolRole that = (SchoolRole) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @Override
    public String toString() {
        return "SchoolRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
