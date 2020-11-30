package com.binarybeasts.voyalsuper.service;

import com.binarybeasts.voyalsuper.model.DAOUser;
import com.binarybeasts.voyalsuper.model.ShoppingCart;
import com.binarybeasts.voyalsuper.model.dto.RegisterUserDto;
import com.binarybeasts.voyalsuper.model.dto.ShoppingCartDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface UserService {

    DAOUser save(RegisterUserDto user);

    DAOUser getUser(String token);

    List<ShoppingCart> getUserFavoriteShoppingCarts(Map<String, String> headers);

    ShoppingCart addShoppingCartToUser(Map<String, String> headers, ShoppingCartDto shoppingCartDto);

    void updateUser(Long userId, RegisterUserDto user, String token);
}
