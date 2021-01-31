package com.singup.demo.signupdemo.service;

import com.singup.demo.signupdemo.dto.UserDTO;
import com.singup.demo.signupdemo.model.User;

public interface SignUpService {
    User save(UserDTO user);
}
