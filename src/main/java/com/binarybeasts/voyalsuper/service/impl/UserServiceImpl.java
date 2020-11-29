package com.binarybeasts.voyalsuper.service.impl;

import com.binarybeasts.voyalsuper.exception.BadRequestException;
import com.binarybeasts.voyalsuper.model.DAOUser;
import com.binarybeasts.voyalsuper.model.dto.RegisterUserDto;
import com.binarybeasts.voyalsuper.repository.UserRepository;
import com.binarybeasts.voyalsuper.security.jwt.JwtUtil;
import com.binarybeasts.voyalsuper.service.UserService;
import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public DAOUser save(RegisterUserDto user) {
        if (userRepository.existsByUsername(user.getEmail()))
            throw new BadRequestException("Ya existe un usuario con ese email");

        DAOUser daoUser = new DAOUser(user.getEmail(), bcryptEncoder.encode(user.getPassword()), user.getEmail(), user.getRole(), false );
        return userRepository.save(daoUser);
    }


    @Override
    public DAOUser getUser(String token) {
        return userRepository.findByUsername(jwtUtil.extractUsername(token)).orElseThrow(() -> new NotFoundException("No se encontro el usuario"));
    }

    @Override
    public void updateUser(Long userId, RegisterUserDto user, String token) {

    }

    private DAOUser getUserFromToken(String token){
        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }
        return userRepository.findByUsername(jwtUtil.extractUsername(token)).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
    }
}
