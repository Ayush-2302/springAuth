package com.springauth.springauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springauth.springauth.entities.Users;


public interface UserRepo extends JpaRepository<Users, Integer> {

  Users findByEmail(String userName);

}
