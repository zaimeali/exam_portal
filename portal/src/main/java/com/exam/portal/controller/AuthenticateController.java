package com.exam.portal.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.config.JwtUtils;
import com.exam.portal.entity.JwtRequest;
import com.exam.portal.entity.JwtResponse;
import com.exam.portal.entity.User;
import com.exam.portal.service.implementation.UserDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthenticateController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {

            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        } catch (UsernameNotFoundException err) {
            err.printStackTrace();
            throw new Exception("User not found");
        }

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException err) {
            throw new Exception("USER DISABLED - " + err.getMessage());
        } catch(BadCredentialsException err) {
            throw new Exception("Invalid Credentials - " + err.getMessage());
        }
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return (User) userDetailsServiceImpl.loadUserByUsername(principal.getName());
    }
}
