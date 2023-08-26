package com.example.bankbackend.role;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RoleFacade {

    RoleRepository roleRepository;

    public RoleFacade(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRole(String roleName){
        return roleRepository.findByRole(roleName)
                .orElseThrow(() -> new NoSuchElementException("There is no role with name : " + roleName));
    }
}
