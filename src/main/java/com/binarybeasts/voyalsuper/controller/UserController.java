package com.binarybeasts.voyalsuper.controller;

import com.binarybeasts.voyalsuper.model.ShoppingCart;
import com.binarybeasts.voyalsuper.model.dto.ShoppingCartDto;
import com.binarybeasts.voyalsuper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getFavoriteShoppingCarts")
    public List<ShoppingCart> getUserFavoriteShoppingCarts(@RequestHeader Map<String, String> headers){
        List<ShoppingCart> toReturn = userService.getUserFavoriteShoppingCarts(headers);
        return toReturn;
    }

    @PostMapping("/addFavoriteShoppingCart")
    public ShoppingCart addShoppingCartToUserFavorites(@RequestHeader Map<String, String> headers, ShoppingCartDto shoppingCartDto){
        return userService.addShoppingCartToUser(headers, shoppingCartDto);
    }


}
