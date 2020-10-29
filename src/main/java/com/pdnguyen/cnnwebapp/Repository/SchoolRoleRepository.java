package com.pdnguyen.cnnwebapp.Repository;

import com.pdnguyen.cnnwebapp.Entity.SchoolRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * PERSISTENCE LAYER
 *
 */
@Repository
public interface SchoolRoleRepository extends JpaRepository<SchoolRole, Integer> {

}
