package com.example.demo.dto.msg;

import com.example.demo.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRespMsg {
    private UserDto userDto;
}
