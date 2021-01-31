package com.singup.demo.signupdemo.service;

import com.singup.demo.signupdemo.dto.UserDTO;
import com.singup.demo.signupdemo.model.User;
import com.singup.demo.signupdemo.repositories.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ISignUpService implements SignUpService{

    @Autowired
    private SignUpRepository signUpRepository;

    @Override
    public User save(UserDTO user) {
        return signUpRepository.save(dtoToMap(user)).block();
    }

    private User dtoToMap(UserDTO dto){
        return User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .status(dto.isStatus())
                .build();
    }
}
