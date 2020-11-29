package com.binarybeasts.voyalsuper.service;

import com.binarybeasts.voyalsuper.model.DAOUser;
import com.binarybeasts.voyalsuper.model.dto.RegisterUserDto;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserService {

    DAOUser save(RegisterUserDto user);

    DAOUser getUser(String token);

    void updateUser(Long userId, RegisterUserDto user, String token);
}
