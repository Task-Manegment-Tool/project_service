package com.blue.project_service.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

//this enttiy is for user

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;

    private String username;

    private String password;

    private String email;


    private String phoneNumber;


}



