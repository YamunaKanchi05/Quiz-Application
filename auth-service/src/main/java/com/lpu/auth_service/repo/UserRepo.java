package com.lpu.auth_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpu.auth_service.model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
