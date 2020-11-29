package com.binarybeasts.voyalsuper.service;

import com.binarybeasts.voyalsuper.model.DAOUser;
import com.binarybeasts.voyalsuper.model.dto.RegisterUserDto;

import java.util.Map;

public interface UserService {

    void save(RegisterUserDto user, Map<String, String> headers);

    DAOUser getUser(long userId, String token);

    void updateUser(Long userId, RegisterUserDto user, String token);
}
