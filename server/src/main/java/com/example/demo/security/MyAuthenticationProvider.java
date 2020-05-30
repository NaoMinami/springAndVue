package com.example.demo.security;

import com.example.demo.dto.msg.LoginReqMsg;
import com.example.demo.service.UserAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {
    private final UserAppService userAppService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        System.out.println("ProviderStart");
        LoginReqMsg loginReqMsg = (LoginReqMsg) authentication.getPrincipal();
        if (loginReqMsg == null) {
            throw new BadCredentialsException("LoginReqMsg is not found");
        }
        System.out.println("ProviderFinish::"+loginReqMsg);
        return userAppService.login(loginReqMsg); //usernamePassword....Tokenが返る
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
