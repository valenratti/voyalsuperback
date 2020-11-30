package com.binarybeasts.voyalsuper.service.impl;

import com.binarybeasts.voyalsuper.model.DAOUser;
import com.binarybeasts.voyalsuper.model.ShoppingCart;
import com.binarybeasts.voyalsuper.security.jwt.JwtUtil;
import com.binarybeasts.voyalsuper.service.ShoppingCartService;
import com.binarybeasts.voyalsuper.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    public List<ShoppingCart> findUserFavouriteCarts(Map<String, String> headers) {
        DAOUser user = userService.getUser(jwtUtil.extractUsername(headers.get("authorization")));
        return user.getFavouriteShoppingCarts();
    }


}
