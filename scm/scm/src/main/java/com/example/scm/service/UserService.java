package com.example.scm.service;

import com.example.scm.domain.Role;
import com.example.scm.domain.User;
import com.example.scm.domain.enums.RoleType;
import com.example.scm.dto.request.RegisterRequest;
import com.example.scm.exception.ConflictException;
import com.example.scm.exception.ResourceNotFoundException;
import com.example.scm.exception.message.ErrorMessage;
import com.example.scm.repository.UserRepository;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;


    private RoleService roleService;


    private PasswordEncoder passwordEncoder;



    public UserService (UserRepository userRepository, RoleService roleService, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository=userRepository;
        this.roleService=roleService;
        this.passwordEncoder=passwordEncoder;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, email)));
    }

    public void saveUserWithRetailerRole(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()));
        }

//        roleService.createRoles();
        Role role= roleService.findByType(RoleType.ROLE_RETAILER);

        Set<Role> roles=new HashSet<>();
        roles.add(role);

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user =new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPassword);
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setRoles(roles);

        userRepository.save(user);

    }

    public void saveUserWithUserRole(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()));
        }

//        roleService.createRoles();
        Role role= roleService.findByType(RoleType.ROLE_USER);

        Set<Role> roles=new HashSet<>();
        roles.add(role);

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user =new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPassword);
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setRoles(roles);

        userRepository.save(user);

    }



}
