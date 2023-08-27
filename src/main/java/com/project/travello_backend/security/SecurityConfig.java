package com.project.travello_backend.security;


import com.project.travello_backend.ExceptionHandlers.CustomAccessDeniedHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    private static final List<String> ALLOWED_ORIGINS = List.of("http://localhost:4200");
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf->csrf.disable())
                .cors(cors -> {
                    cors.configurationSource(corsConfigurationSource());
                })

                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/api/customer/getallcustomers").hasAuthority("ADMIN");
                    auth.requestMatchers("/api/customer/editcustomer/**").hasAuthority("ADMIN");
                    auth.requestMatchers("/api/customer/delete/**").hasAuthority("ADMIN");
                    auth.requestMatchers("api/hotel/addhotel").hasAuthority("ADMIN");
                    auth.requestMatchers("api/hotel/addroom/**").hasAuthority("ADMIN");
                    auth.requestMatchers("api/hotel/edithotel/**").hasAuthority("ADMIN");
                    auth.requestMatchers("api/hotel/delete").hasAuthority("ADMIN");
                    auth.requestMatchers("/ws/**", "/sockjs/**").permitAll();
                    auth.anyRequest().permitAll();

                })
                .exceptionHandling((exception)->{exception.accessDeniedHandler(accessDeniedHandler);})
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration corsConfiguration=new CorsConfiguration();

                corsConfiguration.setAllowedOrigins(ALLOWED_ORIGINS);
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.addAllowedHeader("*");
//                corsConfiguration.addAllowedOriginPattern("**");
                corsConfiguration.addAllowedMethod(HttpMethod.GET);
                corsConfiguration.addAllowedMethod(HttpMethod.POST);
                corsConfiguration.addAllowedMethod(HttpMethod.PUT);
                corsConfiguration.addAllowedMethod(HttpMethod.DELETE);


                return corsConfiguration;
            }
        };
    }

}
