package com.binarybeasts.voyalsuper.service;

import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.dto.MarketProductDto;
import com.binarybeasts.voyalsuper.model.dto.SupermarketDto;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import org.springframework.http.ResponseEntity;

public interface SupermarketService {

    Supermarket addSupermarket(SupermarketDto supermarketDto);

    ResponseEntity<?> addMarketProduct(MarketProductDto marketProductDto);
}
