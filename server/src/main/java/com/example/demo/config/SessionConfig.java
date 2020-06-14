/*package com.example.demo.config;

import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@EnableJdbcHttpSession
@ConditionalOnWebApplication
public class SessionConfig {
    @Bean
    public JdbcIndexedSessionRepository sessionRepository(
            JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        JdbcIndexedSessionRepository sessionRepository = new JdbcIndexedSessionRepository(jdbcTemplate, transactionTemplate);
        sessionRepository.setDefaultMaxInactiveInterval(24 * 60 * 60);
        return sessionRepository;
    }

    @Bean
    @SuppressWarnings({"unchecked","rawtypes"})
    public SessionRegistry sessionRegistry(FindByIndexNameSessionRepository sessionRepository) {
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }

    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
        SessionFixationProtectionStrategy sessionFixationProtectionStrategy = new SessionFixationProtectionStrategy();
        sessionFixationProtectionStrategy.setMigrateSessionAttributes(false);
        sessionFixationProtectionStrategy.setAlwaysCreateSession(true);

        return new CompositeSessionAuthenticationStrategy(Lists.newArrayList(sessionFixationProtectionStrategy));
    }
}*/
