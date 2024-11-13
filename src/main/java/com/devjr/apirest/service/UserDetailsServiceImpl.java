package com.devjr.apirest.service;

import com.devjr.apirest.model.UserEntity;
import com.devjr.apirest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public static final Logger logger=LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserEntityByUsername(username);

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRolesEntities()
                .forEach(rolesEntity -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(rolesEntity.getRoleEnum().name()))));

        userEntity.getRolesEntities().stream()
                .flatMap(rolesEntity -> rolesEntity.getPermissionEntitySet().stream())
                .forEach(permissionEntity -> authorityList.add(new SimpleGrantedAuthority(permissionEntity.getName())));

        logger.info(userEntity.getUsername()+"\n");
        logger.info(userEntity.getPassword()+"\n");
        logger.info(authorityList+"\n");

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                authorityList);
    }
}
