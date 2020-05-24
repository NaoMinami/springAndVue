package com.example.demo.security;

import com.example.demo.utils.JacksonHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyAuthenticationProvider myAuthenticationProvider;
    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private final JacksonHelper jacksonHelper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter =
                new MyUsernamePasswordAuthenticationFilter(jacksonHelper);
        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);

        http
                .csrf().disable()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/login").permitAll()
                        .antMatchers("/app/*").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .addFilter(myUsernamePasswordAuthenticationFilter);
                http.cors().configurationSource(this.corsConfigurationSource());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addExposedHeader("Authorization");
        corsConfiguration.addAllowedOrigin("http://localhost:8080");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }

}
