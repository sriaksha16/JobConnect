package com.example.jobconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index", "/css/**", "/js/**","/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
                                 "/jobseekerlogin",
                                 "/jobseekerregister",
                                 "/jobseeker/register",
                                 "/jobseeker/verify",
                                 "/jobseeker/login",
                                 "/jobseeker/forgotpassword",
                                 "/jobseeker/otp",
                                 "/jobseeker/verify-otp",
                                 "/jobseeker/reset-password",
                                 "/jobseeker/profile",
                                 "/jobseeker/findjobs",
                                 "/jobseeker/applyjob",
                                 "/jobseeker/back",
                                 "/employerlogin",
                                 "/employerregister",
                                 "/employer/register",
                                 "/employer/verify",
                                 "/employer/login",
                                 "/employer/forgotpassword",
                                 "/employer/otp",
                                 "/employer/verify-otp",
                                 "/employer/reset-password",
                                 "/employer/profile",
                                 "/employer/postjob",
                                 "/employer/back",
                                 "/employer/savejob",
                                 "/employer/myjob",
                                 "/employer/jobs/{id}/edit",
                                 "/employer/jobs/{id}/delete",
                                 "/employer/jobapplications",
                                 "/employer/download/resume/{id}",
                                 "/employer/download/coverletter/{id}",
                                 "/employer/logout",
                                 "/jobseeker/logout"
                ).permitAll()
                .requestMatchers("/employer/**").hasRole("EMPLOYER")
                .requestMatchers("/jobseeker/**").hasRole("JOBSEEKER")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    var authorities = authentication.getAuthorities();
                    if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYER"))) {
                        response.sendRedirect("/employer/home");
                    } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_JOBSEEKER"))) {
                        response.sendRedirect("/jobseeker/home");
                    } else {
                        response.sendRedirect("/");
                    }
                })
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/employer/logout")
                .logoutSuccessUrl("/employerlogin?logout") 
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/jobseeker/logout")
                .logoutSuccessUrl("/jobseekerlogin?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
