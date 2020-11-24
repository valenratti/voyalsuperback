package com.binarybeasts.voyalsuper.service.impl;

import com.binarybeasts.voyalsuper.model.MarketProduct;
import com.binarybeasts.voyalsuper.model.Product;
import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.dto.MarketProductDto;
import com.binarybeasts.voyalsuper.model.dto.SupermarketDto;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.repository.MarketProductRepository;
import com.binarybeasts.voyalsuper.repository.ProductRepository;
import com.binarybeasts.voyalsuper.repository.SupermarketRepository;
import com.binarybeasts.voyalsuper.service.ProductService;
import com.binarybeasts.voyalsuper.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SupermarketServiceImpl implements SupermarketService {

    @Autowired
    SupermarketRepository supermarketRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MarketProductRepository marketProductRepository;

    @Autowired
    ProductService productService;

    @Override
    public Supermarket addSupermarket(SupermarketDto supermarketDto) {
        return supermarketRepository.save(new Supermarket(supermarketDto.getName()));
    }

    @Override
    public ResponseEntity<?> addMarketProduct(MarketProductDto marketProductDto) {
        Supermarket toAdd = supermarketRepository.findByName(marketProductDto.getSupermarket()).orElseThrow( () -> new NoSuchElementException("No se ha encontrado el supermercado al cual desea agregar el producto"));
        Product product = productRepository.findByEan(marketProductDto.getEan()).orElseThrow( () -> new NoSuchElementException("No se ha encontrado el producto que desea agregar"));
        MarketProduct marketProduct = new MarketProduct(product,marketProductDto.getPrice(),marketProductDto.getUrl(),toAdd);
        marketProductRepository.save(marketProduct);
        toAdd.addProduct(marketProduct);
        supermarketRepository.save(toAdd);
        productService.addSupermarketWithStockOfProduct(product,marketProductDto.getSupermarket());
        return ResponseEntity.ok().build();
    }
}
