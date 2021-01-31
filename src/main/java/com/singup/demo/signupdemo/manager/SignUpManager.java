package com.singup.demo.signupdemo.manager;

import com.singup.demo.signupdemo.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SignUpManager {

    public int validate(UserDTO userDTO){
        try{
            if(userDTO.getName().isEmpty()){
                return HttpStatus.BAD_REQUEST.value();
            }
            if(userDTO.getEmail().isEmpty()){
                return HttpStatus.BAD_REQUEST.value();
            }
            if(userDTO.getPassword().isEmpty()){
                return HttpStatus.BAD_REQUEST.value();
            }
            if(userDTO.getPasswordCheck().isEmpty()){
                return HttpStatus.BAD_REQUEST.value();
            }
            if(!userDTO.getPassword().equals(userDTO.getPasswordCheck())){
                return HttpStatus.BAD_REQUEST.value();
            }
            return HttpStatus.ACCEPTED.value();
        }
        catch (Exception e){
            return HttpStatus.BAD_REQUEST.value();
        }
    }
}
