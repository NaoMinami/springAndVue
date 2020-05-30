package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@org.springframework.data.relational.core.mapping.Table("USERS")
public class Users {
    @Id
    private String userId;

    private String loginId;
    private String password;
    private String userGroup;
}
