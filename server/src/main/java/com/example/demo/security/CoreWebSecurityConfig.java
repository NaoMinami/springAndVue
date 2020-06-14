package com.example.demo.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@ConditionalOnWebApplication
public class CoreWebSecurityConfig {

    @Bean("authenticationEntryPoint")
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new Http403ForbiddenEntryPoint();
    }

    @Bean("firewall")
    public HttpFirewall httpFirewall() {
        DefaultHttpFirewall httpFirewall = new DefaultHttpFirewall();
        httpFirewall.setAllowUrlEncodedSlash(true);
        return httpFirewall;
    }
}
