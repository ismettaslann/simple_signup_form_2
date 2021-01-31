package com.singup.demo.signupdemo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String email;
    private String name;
    private String password;
    @Builder.Default
    private boolean status = true;
}


