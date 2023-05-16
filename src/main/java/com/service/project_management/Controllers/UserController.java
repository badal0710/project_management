package com.service.project_management.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.project_management.Entities.User;
import com.service.project_management.dto.LoginRequest;
import com.service.project_management.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{email}")
    public Integer getUser(@PathVariable String email) {
        return this.userService.findByMail(email);
    }

    @PostMapping("/insertUser")
    public User InsertUser(@RequestBody User user) {
        return this.userService.insertUser(user);
    }

    @PostMapping("/login")
    public Integer login(@RequestBody LoginRequest body){
        return this.userService.loginUser(body);
    }
}
