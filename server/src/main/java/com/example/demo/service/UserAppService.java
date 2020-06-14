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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
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
        System.out.println(authorities);
        UserDto userDto = UserDto.of(users);
        System.out.println("Servicefinish:"+userDto);
        System.out.println(loginReqMsg.getPassword());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDto, loginReqMsg.getPassword(), authorities);
        System.out.println("principal::"+usernamePasswordAuthenticationToken.getPrincipal());
        System.out.println("credential::"+usernamePasswordAuthenticationToken.getCredentials());
        System.out.println("authori::"+ usernamePasswordAuthenticationToken.getAuthorities());
        return usernamePasswordAuthenticationToken;
    }
}
