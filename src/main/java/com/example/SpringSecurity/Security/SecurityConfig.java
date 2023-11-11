package com.example.SpringSecurity.Security;

import com.example.SpringSecurity.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;


   @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth->auth
                        //.requestMatchers("/livre").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario").permitAll()
                        .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    static PasswordEncoder psEncode(){
        return new BCryptPasswordEncoder();
    }

    protected void authManager(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsServiceImpl);
    }

   /* @Bean
    UserDetailsService user(){
        UserDetails user = User.builder()
                .username("Eduardo")
                .password(psEncode().encode("123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }*/


}
