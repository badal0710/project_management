package com.service.project_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.project_management.Entities.User;
import com.service.project_management.Repositories.UserRepo;
import com.service.project_management.dto.LoginRequest;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User insertUser(User user) {
        return this.userRepo.save(user);
    }

    public Integer findByMail(String email) {
        String typeofUser = this.userRepo.findByEmail(email);
        if (typeofUser != null && typeofUser.equals("admin")) {
            return 1;
        } else if (typeofUser != null && typeofUser.equals("investor")) {
            return 2;
        } else if (typeofUser != null && typeofUser.equals("contractor")) {
            return 3;
        } else {
            return 404;
        }
    }

    public Integer loginUser(LoginRequest data){
        User user = this.userRepo.loginUser(data.getUpn(),data.getPsw());

        if(user==null){
            return 404;
        }

        if(user.getTypeOfUser()=="admin"){
            return 1;
        }else if(user.getTypeOfUser()=="investor"){
            return 2;
        }else if(user.getTypeOfUser()=="contractor"){
            return 3;
        }else{
            return 404;
        }
    }

}