package com.example.demo.dto;

import com.example.demo.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonIgnore
    private String userId;

    private String loginId;

    public static UserDto of(Users users) {
        return new UserDto(
                users.getUserId(),
                users.getLoginId()
        );
    }
}
