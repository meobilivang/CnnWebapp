package com.pdnguyen.cnnwebapp.Repository.Custom;

import com.pdnguyen.cnnwebapp.Entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * PERSISTENCE LAYER
 * Customized Database access operations
 */
@Repository
public class UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Find User by Username
     * @param userName
     * @return
     */
    public User findByUserName(String userName) {
        User userInstance = null;
        try {
            TypedQuery<User> sqlQuery = entityManager.createQuery("SELECT anUser FROM User anUser WHERE anUser.user_name=:userName", User.class);
            sqlQuery.setParameter("userName", userName);
            sqlQuery.setFirstResult(0);
            sqlQuery.setMaxResults(1);
            userInstance = (User) sqlQuery.getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return userInstance;
    }

    /**
     * Find User by email
     * @param inputEmail
     * @return
     */
    public User findByEmail(String inputEmail) {
        User userInstance = null;
        try {
            TypedQuery<User> sqlQuery = entityManager.createQuery("SELECT u FROM User u WHERE u.email=:inputEmail", User.class);
            sqlQuery.setParameter("inputEmail", inputEmail);
            //Limit number of queries
            sqlQuery.setFirstResult(0);
            sqlQuery.setMaxResults(1);
            userInstance = (User) sqlQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInstance;
    }

}
