package com.binarybeasts.voyalsuper.service;

import com.binarybeasts.voyalsuper.model.DAOUser;
import com.binarybeasts.voyalsuper.model.ShoppingCart;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {

    List<ShoppingCart> findUserFavouriteCarts(Map<String, String> headers);
}
