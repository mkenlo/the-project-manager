package com.mkenlo.theprojectmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class WebSecurityConfig {

        @Autowired
        HandlerMappingIntrospector introspector;

        private UserDetailsService userDetailsService;

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(
                                                auth -> auth.requestMatchers(
                                                                new MvcRequestMatcher(introspector, "/projects/**"),
                                                                new MvcRequestMatcher(introspector, "/tasks/**"),
                                                                new MvcRequestMatcher(introspector, "/dashboard"))
                                                                .authenticated()
                                                                .anyRequest().permitAll())
                                .formLogin(
                                                form -> form.loginPage("/login")
                                                                .defaultSuccessUrl("/dashboard")
                                                                .permitAll())
                                .logout(
                                                logout -> logout.logoutUrl("/logout")
                                                                .permitAll());
                return http.build();

        }

        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        }
}