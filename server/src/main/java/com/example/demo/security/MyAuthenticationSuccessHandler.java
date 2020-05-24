package com.example.demo.security;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.msg.LoginRespMsg;
import com.example.demo.utils.JacksonHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JacksonHelper jacksonHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
        System.out.println("SucessHandlerStart");
        UserDto userDto = (UserDto) authentication.getPrincipal();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jacksonHelper.getDefaultObjectMapper().writeValueAsString(new LoginRespMsg(userDto)));
        response.getWriter().flush();
        System.out.println("SucessHandleFinish");
    }
}
