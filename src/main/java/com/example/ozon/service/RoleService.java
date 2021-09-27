package com.example.ozon.service;

import com.example.ozon.domain.Role;
import com.example.ozon.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    Role findRoleByName(String name){
        return roleRepository.findRoleByName(name).orElseThrow(RuntimeException::new);
    }
}
