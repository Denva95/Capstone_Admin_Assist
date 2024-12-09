package org.ndissandea.adminassist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //Marks this class as a configuration class for Spring, enabling it to define beans and configure settings.
@EnableWebSecurity // Enable spring security to the application
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService; //Injects a custom implementation of the UserDetailsService interface to load user-specific data.

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        //// Configures the authentication provider with the UserDetailsService and password encoder.
        DaoAuthenticationProvider authUser = new DaoAuthenticationProvider();
        authUser.setUserDetailsService(userDetailsService);
        authUser.setPasswordEncoder(passwordEncoder()); //Use for encode the password (hashing)
        return authUser;
    }

    //Provides the AuthenticationManager, central to Spring Security's authentication process.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Defines the password encoder bean, using BCrypt with a strength of 11.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    // Configures security rules, specifying access permissions, login, and logout settings.
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Allows unauthenticated access to public pages, static resources, and signup endpoints.
                        .requestMatchers("/", "/login*", "/css/**", "/js/**", "/sign-up", "/signup-process").permitAll()
                        // Restricts access to "/home" to users with roles "ADMIN" or "ASSISTANT".
                        .requestMatchers("/home").hasAnyRole("ADMIN", "ASSISTANT")
                        // Restricts access to "/dashboard" (dashboard admin) to users with roles ADMIN.
                        .requestMatchers("/dashboard").hasRole("ADMIN")
                        // Restricts access to assistant dashboard to users with roles Assistant.
                        .requestMatchers("/dashboard_assistant").hasRole("ASSISTANT")
                        //Restricts access to employee profile, Inventory management and task
                        .requestMatchers("/employees/**", "/inventory/**").hasAnyRole("ADMIN", "ASSISTANT")
                        .requestMatchers("/task/**").hasAnyRole("ADMIN", "ASSISTANT")
                        //Restricts access to department management to ADMIN
                        .requestMatchers("/department/**").hasRole("ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/login") //Specifies a custom login page.
                        .loginProcessingUrl("/login") // Configures the URL to process login requests.
                        .defaultSuccessUrl("/home", true) // Redirects authenticated users to "/home" after successful login.

                        // Grants public access to the login page and processing URL.
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true) // Invalidates the HTTP session upon logout for security.
                        .clearAuthentication(true)  // Clears authentication details during logout.
                        .permitAll()
                );
        return http.build(); // Finalizes and returns the configured HttpSecurity object.
    }
}