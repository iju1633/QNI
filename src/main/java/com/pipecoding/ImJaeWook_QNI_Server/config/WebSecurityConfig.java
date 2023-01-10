package com.pipecoding.ImJaeWook_QNI_Server.config;

import com.pipecoding.ImJaeWook_QNI_Server.util.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class WebSecurityConfig {

    private final ApplicationProperties applicationProperties;


    @Autowired
    public WebSecurityConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // hash 의 강도 : default 는 10
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .configurationSource(new CorsConfig().corsConfigurationSource())

                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(applicationProperties.getWhiteListURLs().toArray(new String[0]))
                .permitAll()

                .and()

                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK)
                        )
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "remember-me"))
                .build();
    }
}