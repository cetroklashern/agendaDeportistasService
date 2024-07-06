package com.agendadeportistas.agendaservices.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.agendadeportistas.agendaservices.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class is a custom filter for Spring Security that handles JWT authentication.
 * It extends the OncePerRequestFilter class and overrides the doFilterInternal method.
 * This method retrieves the JWT token from the HTTP request header, validates it, and sets up the authentication context.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    /**
    * This method retrieves the JWT token from the HTTP request header.
    * 
    * @param request The HTTP request object containing the Authorization header.
    * @return The JWT token if present and starts with "Bearer ", otherwise null.
    */
    private String obtenerTokenSolicitud(HttpServletRequest request){
        // Extract the Authorization header from the request
        String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
        
        // Check if the Authorization header is present and starts with "Bearer "
        if(StringUtils.hasText(authHeader) && authHeader.startsWith(SecurityConstants.TOKEN_PREFIX)){
            // If the conditions are met, extract and return the JWT token
            return authHeader.substring(7, authHeader.length());
        }
        
        // If the conditions are not met, return null
        return null;
    }

    /**
     * This class is a custom filter for Spring Security that handles JWT authentication.
     * It extends the OncePerRequestFilter class and overrides the doFilterInternal method.
     * This method retrieves the JWT token from the HTTP request header, validates it, and sets up the authentication context.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain) throws ServletException, IOException {
        
        // Retrieve the JWT token from the request header
        String token = obtenerTokenSolicitud(request);
        
        // Check if the token is present and valid
        if(StringUtils.hasText(token) && jwtTokenProvider.validarToken(token)){
            // Extract the username from the token
            String username = jwtTokenProvider.obtenerUsernameDeJwt(token);
            
            // Load the user details from the database using the username
            UserDetails userDetails = userService.loadUserByUsername(username);

            // Extract the user roles from the user details
            List<String> userRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            
            // Check if the user has the required roles (USER or ADMIN)
            if(userRoles.contains("USER") || userRoles.contains("ADMIN")){
                // Create a new authentication token with the user details and roles
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, 
                    null, userDetails.getAuthorities());

                // Set the authentication details in the authentication token
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // Set the authentication token in the security context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }            
        }
        
        // Continue the filter chain
        filterChain.doFilter(request, response);
    }    
}