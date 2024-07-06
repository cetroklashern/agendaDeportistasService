package com.agendadeportistas.agendaservices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Indica que es una clase de configuración
@EnableWebSecurity // Indica que esta clase activa y configura la seguridad web
public class SecurityConfig {
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    //Bean encargado de verificar la información de los usuarios que hacen login
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();        
    }

    //Bean encargado de encriptar las contraseñas
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Bean encargado de manejar el filtro de seguridad de JsonWebToken
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() { 
        return new JwtAuthenticationFilter();
    }

    //Bean encargado de establecer una cadena de filtros de seguridad en la aplicación y 
    //determinar los permisos del usuario logueado
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .exceptionHandling() // permite manejo de excepciones
            .authenticationEntryPoint(jwtAuthenticationEntryPoint) //establece un punto de entrada personalizado de autenticacion
            .and()
            .sessionManagement() //permie la gestion de sesiones
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
            .and()
            .authorizeHttpRequests() //toda peticion debe ser autorizada
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/cursos/crear").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.GET,"/api/cursos/listar").hasAnyAuthority("ADMIN" , "USER")
            .requestMatchers(HttpMethod.GET,"/api/cursos/listarNombre/**").hasAnyAuthority("ADMIN" , "USER")
            .requestMatchers(HttpMethod.DELETE,"/api/cursos/eliminar/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/cursos/actualizar").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
            .httpBasic();
        
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}
