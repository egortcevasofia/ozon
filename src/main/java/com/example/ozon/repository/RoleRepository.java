package com.example.ozon.repository;

import com.example.ozon.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleById(Long id);
    Optional<Role> findRoleByName(String name);
    Role save (Role role);
}
