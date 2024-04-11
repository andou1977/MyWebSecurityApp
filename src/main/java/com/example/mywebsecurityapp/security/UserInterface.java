package com.example.mywebsecurityapp.security;


import com.example.mywebsecurityapp.entity.RoleEntity;
import com.example.mywebsecurityapp.entity.UserEntity;

import java.util.List;

public interface UserInterface {
    List<UserEntity> listalluser();
    List<RoleEntity>listallrole();
    UserEntity saveuser(UserEntity user);


    RoleEntity saverole(RoleEntity role);


    void saveroleanduser(String username,String rolename);


  UserEntity findByUsername(String username);
  UserEntity Searchbyid(int id);
}
