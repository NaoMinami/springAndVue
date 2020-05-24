package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.security.SystemAuthority;
import com.google.common.collect.Lists;

import com.example.demo.dto.msg.LoginReqMsg;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserAppService {

    private final UserRepository userRepository;

    public UsernamePasswordAuthenticationToken login(LoginReqMsg loginReqMsg) {
        System.out.println("ServiceStart");
        Users users = userRepository.findByLoginId(loginReqMsg.getLoginId()).orElseThrow(()
            -> new BadCredentialsException("Uer not found"));
        System.out.println("User found:"+users);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (! Objects.equals(loginReqMsg.getPassword(), users.getPassword())) {
            throw new BadCredentialsException("Password is incorrect");
        }

        List<GrantedAuthority> authorities = Lists.newArrayList(SystemAuthority.ROLE_USER);
        if (users.getUserGroup().equals(SystemAuthority.ROLE_ADMIN.getAuthority())) {
            authorities.add(SystemAuthority.ROLE_ADMIN);
        }
        UserDto userDto = UserDto.of(users);
        System.out.println("Servicefinish:"+userDto);
        return new UsernamePasswordAuthenticationToken(userDto, loginReqMsg.getPassword(), authorities);
    }
}
