package com.agendadeportistas.agendaservices.services;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agendadeportistas.agendaservices.entities.RolesEntity;
import com.agendadeportistas.agendaservices.entities.UsuariosEntity;
import com.agendadeportistas.agendaservices.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    UserRepository userRepository;
    
    //BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Collection<GrantedAuthority> mapToAutorities(List<RolesEntity> roles){
        return roles.stream().map(
            rolesEntity -> new SimpleGrantedAuthority(rolesEntity.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuariosEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(userEntity.getUsername(), userEntity.getPassword(), mapToAutorities(userEntity.getRoles()));
    }
}