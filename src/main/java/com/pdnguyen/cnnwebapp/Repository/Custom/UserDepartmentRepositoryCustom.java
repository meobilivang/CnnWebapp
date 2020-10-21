package com.pdnguyen.cnnwebapp.Repository.Custom;

import com.pdnguyen.cnnwebapp.Entity.User;
import com.pdnguyen.cnnwebapp.Entity.UserDepartment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/***
 *
 *
 */
@Repository
public class UserDepartmentRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Search for multiple users by Department
     * @param searchDepartment
     * @return
     */
    public List<UserDepartment> findUsersByDepartment(String searchDepartment) {
        List <UserDepartment> usersInDepartment = new ArrayList<>();
        try {
            TypedQuery<UserDepartment> sqlQuery = entityManager.createQuery("SELECT elem FROM UserDepartment elem WHERE elem.department=:department", UserDepartment.class);
            sqlQuery.setParameter("department", searchDepartment);
            usersInDepartment = sqlQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersInDepartment;
    }

    /***
     *
     * @param userId
     * @return
     */
    public List<UserDepartment> findUserById(User userId) {
        List<UserDepartment> users = new ArrayList<>();
        try {
            TypedQuery<UserDepartment> sqlQuery = entityManager.createQuery("SELECT e FROM UserDepartment e WHERE e.userId = :inputUserId"
                                                                        ,UserDepartment.class);
            sqlQuery.setParameter("inputUserId", userId);
            users = sqlQuery.getResultList();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /***
     *
     * @param userName
     * @return
     */
    public List<UserDepartment> findUserByUsername(User userName) {
        List<UserDepartment> userDepartment = null;
        try {
            TypedQuery<UserDepartment> sqlQuery = entityManager.createQuery("SELECT e FROM UserDepartment e WHERE e.userName=:inputUserName", UserDepartment.class);
            sqlQuery.setParameter("inputUserName", userName);
            sqlQuery.setFirstResult(0);
            sqlQuery.setMaxResults(1);
            userDepartment = sqlQuery.getResultList();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return userDepartment;
    }
}
