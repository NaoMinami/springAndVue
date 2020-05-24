package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "USERS")
public class Users {
    @Id
    private String userId;

    private String loginId;
    private String password;
    private String userGroup;
}
