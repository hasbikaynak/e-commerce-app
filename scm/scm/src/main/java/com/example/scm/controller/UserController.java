package com.example.scm.controller;

import com.example.scm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/transactions")
@PreAuthorize("hasRole('USER')")
public class UserController {

    @Autowired
    private UserService userService;


}
