package com.singup.demo.signupdemo.controller;

import com.singup.demo.signupdemo.dto.UserDTO;
import com.singup.demo.signupdemo.manager.SignUpManager;
import com.singup.demo.signupdemo.model.User;
import com.singup.demo.signupdemo.service.ISignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class SignUpController {

    @Autowired
    private ISignUpService signUpService;
    @Autowired
    private SignUpManager manager;

    @PostMapping(path = "/signup/")
    public UserDTO save(@RequestBody UserDTO user){
        try{
            int valid = manager.validate(user);
            if (valid == 202){
                User savedUser = signUpService.save(user);
                UserDTO userDTO = mapToDTO(savedUser, HttpStatus.ACCEPTED.value(), "Ok!");
                return userDTO;
            }
            else{
                return mapToDTO(User.builder().status(false).build(), HttpStatus.BAD_REQUEST.value(), "Hata!");
            }
        }catch (Exception e){
            return mapToDTO(User.builder().status(false).build(), HttpStatus.BAD_GATEWAY.value(), "Hata!");
        }
    }

    private UserDTO mapToDTO(User user, int code, String message){
        return UserDTO.builder().id(user.getId())
                                .name(user.getName())
                                .password(user.getPassword())
                                .passwordCheck(user.getPassword())
                                .email(user.getEmail())
                                .status(user.isStatus())
                                .message(message)
                                .code(code)
                                .build();
    }

}
