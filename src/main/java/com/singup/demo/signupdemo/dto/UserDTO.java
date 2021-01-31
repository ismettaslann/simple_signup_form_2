package com.singup.demo.signupdemo.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String email;
    private String name;
    private String password;
    private String passwordCheck;
    @Builder.Default
    private boolean status = true;
    private String message;
    private int code;
}
