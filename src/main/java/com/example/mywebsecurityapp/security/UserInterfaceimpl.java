package com.example.mywebsecurityapp.security;


import com.example.mywebsecurityapp.Exception.MyException;
import com.example.mywebsecurityapp.entity.RoleEntity;
import com.example.mywebsecurityapp.entity.UserEntity;
import com.example.mywebsecurityapp.repository.RoleRepository;
import com.example.mywebsecurityapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserInterfaceimpl implements UserInterface{

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public UserInterfaceimpl(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<RoleEntity> listallrole() {
        return roleRepository.findAll();
    }

    @Override
    public List<UserEntity> listalluser(){
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public UserEntity saveuser(UserEntity user) {

        String password=user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }




    @Override
    public RoleEntity saverole(RoleEntity role) {
        return roleRepository.save(role);
    }



    @Override
    public void saveroleanduser(String username, String rolename) {
        UserEntity user=userRepository.findByUsername(username);
        RoleEntity role=roleRepository.findByrolename(rolename);
       user.getRoleapp().add(role);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity Searchbyid(int id) {
        return userRepository.findById(id).orElseThrow(()->new MyException("id non trouver"));
    }

}
