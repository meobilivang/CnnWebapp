package com.pdnguyen.cnnwebapp.Repository;

import com.pdnguyen.cnnwebapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * PERSISTENCE LAYER
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
