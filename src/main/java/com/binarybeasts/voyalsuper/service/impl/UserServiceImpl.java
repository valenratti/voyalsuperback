package com.binarybeasts.voyalsuper.service.impl;

import com.binarybeasts.voyalsuper.exception.BadRequestException;
import com.binarybeasts.voyalsuper.model.DAOUser;
import com.binarybeasts.voyalsuper.model.Product;
import com.binarybeasts.voyalsuper.model.ProductOrder;
import com.binarybeasts.voyalsuper.model.ShoppingCart;
import com.binarybeasts.voyalsuper.model.dto.ProductOrderDto;
import com.binarybeasts.voyalsuper.model.dto.RegisterUserDto;
import com.binarybeasts.voyalsuper.model.dto.ShoppingCartDto;
import com.binarybeasts.voyalsuper.repository.MarketProductRepository;
import com.binarybeasts.voyalsuper.repository.ProductRepository;
import com.binarybeasts.voyalsuper.repository.ShoppingCartRepository;
import com.binarybeasts.voyalsuper.repository.UserRepository;
import com.binarybeasts.voyalsuper.security.jwt.JwtUtil;
import com.binarybeasts.voyalsuper.service.UserService;
import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private MarketProductRepository marketProductRepository;

    @Autowired
    private ProductRepository productRepository;




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

    @Override
    public List<ShoppingCart> getUserFavoriteShoppingCarts(Map<String, String> headers) {
        DAOUser user = getUserFromToken(headers.get("authorization"));
        return user.getFavouriteShoppingCarts();
    }

    @Override
    public ShoppingCart addShoppingCartToUser(Map<String, String> headers, ShoppingCartDto shoppingCartDto) {
        DAOUser user = getUserFromToken(headers.get("authorization"));
        List<ShoppingCart> favorites = user.getFavouriteShoppingCarts();
        List<ProductOrder> productOrderList = new ArrayList<>();

        shoppingCartDto.getProductList().forEach( (p) -> {
            Product toAdd = productRepository.findByEan(p.getEan()).orElseThrow(() -> new NoSuchElementException("No se ha encontrado el producto"));
            ProductOrder productOrder = new ProductOrder(toAdd, p.getQuantity());
            productOrderList.add(productOrder);
        });

        ShoppingCart newShoppingCart = new ShoppingCart(productOrderList);
        newShoppingCart.setOwner(user);
        favorites.add(newShoppingCart);
        shoppingCartRepository.save(newShoppingCart);
        userRepository.save(user);
        return newShoppingCart;
    }

    private DAOUser getUserFromToken(String token){
        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }
        return userRepository.findByUsername(jwtUtil.extractUsername(token)).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
    }


}
