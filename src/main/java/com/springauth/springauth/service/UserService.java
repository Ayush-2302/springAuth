package com.springauth.springauth.service;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springauth.springauth.entities.Users;
import com.springauth.springauth.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired 
    private JWTservice jwTservice;

    public Users getUserById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Users createUser(Users user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword()));
        Users save = repo.save(user);

        return save;
    }

    public LinkedList<Users> getAllUser() {
        return (LinkedList<Users>) repo.findAll();
    }

    public String verify(Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwTservice.generateToken(user.getName());

        }
        return "Something went wrong !!";

    }

}
