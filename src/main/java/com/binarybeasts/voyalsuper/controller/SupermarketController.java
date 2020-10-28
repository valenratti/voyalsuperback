package com.binarybeasts.voyalsuper.controller;

import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.dto.MarketProductDto;
import com.binarybeasts.voyalsuper.model.dto.SupermarketDto;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("supermarkets")
public class SupermarketController {

    @Autowired
    SupermarketService supermarketService;

    @RequestMapping("/add")
    public Supermarket addSupermarket(SupermarketDto supermarketDto){
        return supermarketService.addSupermarket(supermarketDto);
    }

    @RequestMapping("/addProduct/{marketName}")
    public ResponseEntity<?> addProductToSupermarket(@PathVariable MarketName marketName, MarketProductDto marketProductDto){
        return supermarketService.addMarketProduct(marketName, marketProductDto);
    }


}
