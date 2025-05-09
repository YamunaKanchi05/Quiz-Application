package com.lpu.auth_service.controller;

import com.lpu.auth_service.model.Users;
import com.lpu.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("register")
    public Users register(@RequestBody Users user) {
        return service.register(user);
    }

    @PostMapping("login")
    public String login(@RequestBody Users user) {
        return service.verify(user);
    }
}
