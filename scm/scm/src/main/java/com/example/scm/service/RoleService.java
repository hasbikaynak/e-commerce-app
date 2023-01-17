package com.example.scm.service;

import com.example.scm.domain.Role;
import com.example.scm.domain.enums.RoleType;
import com.example.scm.exception.ResourceNotFoundException;
import com.example.scm.exception.message.ErrorMessage;
import com.example.scm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findByType(RoleType roleType) {
        return roleRepository.findByType(roleType).orElseThrow(()->new
                ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, roleType.name())));
    }

    public void createRoles(){
        List<Role> roles = new ArrayList<>();
        Role roleSupplier = new Role();
        Role roleRetailer = new Role();
        roleSupplier.setType(RoleType.ROLE_USER);
        roleRetailer.setType(RoleType.ROLE_RETAILER);
        roles.add(roleSupplier);
        roles.add(roleRetailer);
        roleRepository.saveAll(roles);
    }
}
