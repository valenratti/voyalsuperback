package com.binarybeasts.voyalsuper.controller;

import com.binarybeasts.voyalsuper.model.MarketProduct;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.service.MarketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("marketproducts")
public class MarketProductController {

    @Autowired
    MarketProductService marketProductService;

    @RequestMapping("/getAll/{productId}")
    public List<MarketProduct> getAllMarketProducts(@PathVariable Long productId){
        return marketProductService.getMarketProductsByProductId(productId);
    }

    @RequestMapping("/get/{productId}")
    public Optional<MarketProduct> getMarketProductInSupermarket(@PathVariable Long productId, @RequestParam MarketName supermarket){
        return marketProductService.getMarketProductByProductIdAndSupermarket(productId,supermarket);
    }
}
