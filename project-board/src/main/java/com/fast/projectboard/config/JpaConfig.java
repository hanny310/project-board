package com.fast.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> Optional.of("hanny"); //TODO: 스프링시큐리티로 인증 기능을 붙이게 될 때 수정필요(그 전까지 모든 user 값은 hanny로 등록되게 함)

    }
}
