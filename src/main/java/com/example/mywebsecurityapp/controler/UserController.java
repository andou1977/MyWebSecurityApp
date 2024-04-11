package com.example.mywebsecurityapp.controler;


import com.example.mywebsecurityapp.Exception.MyException;
import com.example.mywebsecurityapp.entity.RoleEntity;
import com.example.mywebsecurityapp.entity.UserEntity;
import com.example.mywebsecurityapp.repository.RoleRepository;
import com.example.mywebsecurityapp.repository.UserRepository;
import com.example.mywebsecurityapp.security.UserInterfaceimpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserInterfaceimpl userInterfaceimpl;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, UserInterfaceimpl userInterfaceimpl) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userInterfaceimpl = userInterfaceimpl;
    }

    @GetMapping("/listuser")
    public List<UserEntity> listuser(){
        return userInterfaceimpl.listalluser();
    }
    @GetMapping("/listrole")
    public List<RoleEntity>listrole() {
        return userInterfaceimpl.listallrole();
    }

    @PostMapping("/saveuser")
    public UserEntity saveuser(@RequestBody UserEntity userEntity){
        UserEntity user=userRepository.findByUsername(userEntity.getUsername());
        if(user!=null) {
            throw new MyException("username already exists");

        }else{
            return userInterfaceimpl.saveuser(userEntity);
        }
    }

    @PostMapping("/role")
    public RoleEntity saverole(@RequestBody RoleEntity role){
        return roleRepository.save(role);
    }

    @PostMapping("/saveroletouser")
    public void saveroletouser(String username,String rolename){
        userInterfaceimpl.saveroleanduser(username,rolename);
    }

    @GetMapping("/username/{username}")
    public UserEntity findByUsername(String username){
        UserEntity user=userRepository .findByUsername(username);
       if(user!=null){
           return  userInterfaceimpl.findByUsername(username);
       }
       throw new MyException("username n'existe pas");
    }

    @GetMapping("/searchbyid/{id}")
    public UserEntity findbyid(int id){
        return userInterfaceimpl.Searchbyid(id);
    }
}
