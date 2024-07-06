package com.agendadeportistas.agendaservices.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.agendadeportistas.agendaservices.entities.RolesEntity;
import com.agendadeportistas.agendaservices.entities.UsuariosEntity;
import com.agendadeportistas.agendaservices.repositories.RoleRepository;
import com.agendadeportistas.agendaservices.repositories.UserRepository;
import com.agendadeportistas.agendaservices.security.JwtTokenProvider;
import com.agendadeportistas.agendaservices.shared.dto.AuthRespuestaDto;
import com.agendadeportistas.agendaservices.shared.dto.RegistroDto;

import java.sql.SQLException;
import java.util.Collections;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth/")
public class RestControllerAuth {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public RestControllerAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, 
        UserRepository userRepository, RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("registro")
    public ResponseEntity<String> registrar(@RequestBody RegistroDto registroDto) {
        if(userRepository.existsByUsername(registroDto.getUsername())){
            return new ResponseEntity<>("el usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        /*if(userRepository.existsByEmail(registroDto.getEmail())){
            return new ResponseEntity<>("ya existe un usuario con el correo dado", HttpStatus.BAD_REQUEST);
        }*/
        
        UsuariosEntity usuario = new UsuariosEntity();
        usuario.setUsername(registroDto.getUsername());
        usuario.setNombre(registroDto.getNombre());
        usuario.setEmail(registroDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));
        RolesEntity roles = roleRepository.findByName("USER").get();
        usuario.setRoles(Collections.singletonList(roles));

        try{
        userRepository.save(usuario);
        }catch(Exception e){
            return new ResponseEntity<>("error al crear el usuario: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("usuario creado con exito", HttpStatus.CREATED);
    }    

    @PostMapping("registroAdm")
    public ResponseEntity<String> registrarAdmin(@RequestBody RegistroDto registroDto) {
        if(userRepository.existsByUsername(registroDto.getUsername())){
            return new ResponseEntity<>("el usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        
        if(userRepository.existsByEmail(registroDto.getEmail())){
            return new ResponseEntity<>("ya existe un usuario con el correo dado", HttpStatus.BAD_REQUEST);
        }

        UsuariosEntity usuario = new UsuariosEntity();
        usuario.setUsername(registroDto.getUsername());
        usuario.setNombre(registroDto.getNombre());
        usuario.setEmail(registroDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));
        RolesEntity roles = roleRepository.findByName("ADMIN").get();
        usuario.setRoles(Collections.singletonList(roles));
        
        try{
            userRepository.save(usuario);
        }catch(Exception e){
            return new ResponseEntity<>("error al crear el usuario: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>("usuario administrador creado con exito", HttpStatus.CREATED);
    }

    //Metodo para loguear y obtener un token
    @PostMapping("login")
    public ResponseEntity<AuthRespuestaDto> login(@RequestBody RegistroDto loginDto) {
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);
            
            return new ResponseEntity<>(new AuthRespuestaDto(token), HttpStatus.OK);

        }catch (Exception e) {
            System.out.println("Usuario errado");
        }

        return new ResponseEntity<>(new AuthRespuestaDto(""), HttpStatus.BAD_REQUEST);
    }
}
