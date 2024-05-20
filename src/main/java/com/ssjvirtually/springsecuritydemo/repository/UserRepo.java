package com.ssjvirtually.springsecuritydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssjvirtually.springsecuritydemo.entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);
}
