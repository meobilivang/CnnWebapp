package com.pdnguyen.cnnwebapp.Repository;

import com.pdnguyen.cnnwebapp.Entity.UserDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * PERSISTENCE LAYER
 *
 */
public interface UserDepartmentRepository extends JpaRepository<UserDepartment, Integer> {
}
