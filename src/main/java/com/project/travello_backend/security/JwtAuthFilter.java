package com.project.travello_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String AuthHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if(AuthHeader == null || !AuthHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        jwt = AuthHeader.substring(7);

        userEmail = jwtService.extractUsername(jwt);
        System.out.println(userEmail);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails =userDetailsService.loadUserByUsername(userEmail);
            System.out.println(userDetails);
            if(jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);

    }
}
