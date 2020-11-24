package com.binarybeasts.voyalsuper.controller;

import com.binarybeasts.voyalsuper.model.MarketProduct;
import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.service.MarketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("marketproducts")
public class MarketProductController {

    @Autowired
    MarketProductService marketProductService;

    @GetMapping("/getAll/{productEan}")
    public List<MarketProduct> getAllMarketProducts(@PathVariable String productEan){
        return marketProductService.getMarketProductsByProductEan(productEan);
    }

    @GetMapping("/get/{productEan}")
    public Optional<MarketProduct> getMarketProductInSupermarket(@PathVariable String productEan, @RequestParam MarketName supermarket){
        return marketProductService.getMarketProductByProductEanAndSupermarket(productEan, supermarket);
    }
}
