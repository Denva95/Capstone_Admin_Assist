package org.ndissandea.adminassist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authUser = new DaoAuthenticationProvider();
        authUser.setUserDetailsService(userDetailsService);
        authUser.setPasswordEncoder(passwordEncoder());
        return authUser;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder(12);}

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (authUser) -> authUser
                                .requestMatchers("/", "/login*", "/css/*", "/js/*", "/sign-up", "/signup-process").permitAll()
                                .requestMatchers("/home").hasAnyRole("ROLE_ADMIN", "ROLE_ASSISTANT")
                                .requestMatchers("/dashboard").hasAnyRole("ROLE_ADMIN")
                                .requestMatchers("/department", "/department/", "/department/add", "/department/edit/", "/department/edit/", "/department/delete/", "/department/delete/").hasAnyRole("ROLE_ADMIN", "ROLE_ASSISTANT")
                                .requestMatchers("/employees/", "/employees/", "/employees/add", "/employees/delete/", "/employees/edit/", "/employees/details/").hasAnyRole("ROLE_ADMIN", "ROLE_ASSISTANT")
                                .requestMatchers("/inventory", "/inventory/", "/inventory/add", "/inventory/delete/", "/inventory/edit/").hasAnyRole("ROLE_ADMIN", "ROLE_ASSISTANT")
                                .requestMatchers("/task", "/task/", "/task/add", "/task/delete/", "/task/edit/", "/task/delete/").hasRole("ROLE_ADMIN")
                                .requestMatchers("/task", "/task/details/", "/task/edit/").hasRole("ROLE_ASSISTANT")

                )
                .formLogin(form -> form.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successForwardUrl("/dashboard")
                        .permitAll()
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                );
        return http.build();
    }
    }

