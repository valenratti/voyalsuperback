package com.binarybeasts.voyalsuper.controller;

import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.dto.MarketProductDto;
import com.binarybeasts.voyalsuper.model.dto.SupermarketDto;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("supermarkets")
public class SupermarketController {

    @Autowired
    SupermarketService supermarketService;

    @PostMapping("/add")
    public Supermarket addSupermarket(@RequestBody SupermarketDto supermarketDto){
        return supermarketService.addSupermarket(supermarketDto);
    }

    @PostMapping("/addProduct/{marketName}")
    public ResponseEntity<?> addProductToSupermarket(@RequestBody MarketProductDto marketProductDto){
        return supermarketService.addMarketProduct(marketProductDto);
    }


}
