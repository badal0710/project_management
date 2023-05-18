package com.service.project_management.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.service.project_management.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "select type_of_user from user u where u.email = :email ", nativeQuery = true)
    String findByEmail(@Param("email") String Email);

    @Query(value = "select count(*) from user u where u.email = :email and type_of_user= :type", nativeQuery = true)
    Integer authorizeUser(@Param("email") String Email,@Param("type") String type);

    @Query(value = "select * from user u where u.email = :upn && u.password = :pwd", nativeQuery = true)
    User loginUser(@Param("upn") String upn, @Param("pwd") String pwd);

}
