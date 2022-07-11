package com.example.buyme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String username;

    private String password;
    private String type;
    private Date timestamp;

}
