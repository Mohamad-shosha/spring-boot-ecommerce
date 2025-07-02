package com.shosha.ecommerce.dao;

import com.shosha.ecommerce.entity.User;
import com.shosha.ecommerce.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> getUserByRole(Role role);
}
