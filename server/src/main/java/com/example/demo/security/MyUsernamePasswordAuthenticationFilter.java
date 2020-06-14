package com.example.demo.security;

import com.example.demo.dto.msg.LoginReqMsg;
import com.example.demo.utils.JacksonHelper;
import lombok.SneakyThrows;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JacksonHelper jacksonHelper;

    public MyUsernamePasswordAuthenticationFilter(JacksonHelper jacksonHelper) {
        super();
        this.jacksonHelper = jacksonHelper;
    }

    //@SneakyThrows getReaderするために必要だった
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        System.out.println("running my own version of UsernamePasswordAuthenticationFilter");

        //Postデータ（request)のloginIdとpasswordの入ったReqMsgをとる
        //JsonファイルをLoginReqMsgに変換
        LoginReqMsg loginReqMsg = jacksonHelper.getDefaultObjectMapper()
                .readValue(request.getReader(), LoginReqMsg.class);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginReqMsg,null);

        setDetails(request, authRequest);

        System.out.println("FilterFinish");
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
