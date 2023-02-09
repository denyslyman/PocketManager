package com.pocketmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        {
                            try {
                                authorize.requestMatchers("/sign-up/**").permitAll()
                                        .requestMatchers("/index").permitAll()
                                        .requestMatchers("/users").hasRole("USER")
                                        .requestMatchers("/css/**", "/js/**", "/favicon/**",
                                                "/prime/**", "/sign/**","/svg/**").permitAll()
                                        .and()
                                        .exceptionHandling().accessDeniedPage("/sign-in");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).formLogin(
                        form -> form
                                .loginPage("/sign-in")
                                .loginProcessingUrl("/sign-in")
                                .defaultSuccessUrl("/users")
                                .passwordParameter("password")
                                .usernameParameter("email")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
