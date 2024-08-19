package com.springauth.springauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springauth.springauth.entities.UserPrinciple;
import com.springauth.springauth.entities.Users;
import com.springauth.springauth.repository.UserRepo;

@Service
public class myUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByEmail(username);
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrinciple(user);

    }

}
