package com.example.scm.controller;

import com.example.scm.dto.request.LoginRequest;
import com.example.scm.dto.request.RegisterRequest;
import com.example.scm.dto.response.LoginResponse;
import com.example.scm.dto.response.ResponseMessage;
import com.example.scm.dto.response.SCMResponse;
import com.example.scm.security.jwt.JwtUtils;
import com.example.scm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserJWTController {

        @Autowired
        private UserService userService;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtUtils jwtUtils;

        @PostMapping("/register")
        public ResponseEntity<SCMResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
            userService.saveUserWithUserRole(registerRequest);

            SCMResponse response=new SCMResponse();
            response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
            response.setSuccess(true);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }


        @PostMapping("/login")
        public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest){

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            UserDetails userDetails= (UserDetails) authentication.getPrincipal();

            String jwtToken = jwtUtils.generateJwtToken(userDetails);


            LoginResponse loginResponse=new LoginResponse(jwtToken);

            return new ResponseEntity<>(loginResponse,HttpStatus.OK);

        }
}
