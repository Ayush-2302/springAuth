package com.springauth.springauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springauth.springauth.entities.Users;
import com.springauth.springauth.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getuser")
    public String getUser(HttpServletRequest request) {
        return "Hello, User! " + request.getSession().getId();
    }

    @PostMapping("/createuser")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        try {
            Users createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        System.out.println(user);
        String verify = userService.verify(user);
        return new ResponseEntity<>( verify, HttpStatus.OK);
    }
}
