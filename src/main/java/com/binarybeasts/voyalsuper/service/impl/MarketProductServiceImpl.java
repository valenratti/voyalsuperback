package com.binarybeasts.voyalsuper.service.impl;

import com.binarybeasts.voyalsuper.model.MarketProduct;
import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.repository.MarketProductRepository;
import com.binarybeasts.voyalsuper.repository.SupermarketRepository;
import com.binarybeasts.voyalsuper.service.MarketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MarketProductServiceImpl implements MarketProductService {

    @Autowired
    MarketProductRepository repository;

    @Autowired
    SupermarketRepository supermarketRepository;

    @Override
    public List<MarketProduct> getMarketProductsByProductEan(String ean) {
        return repository.findAllByProduct_Ean(ean);
    }

    @Override
    public Optional<MarketProduct> getMarketProductByProductEanAndSupermarket(String ean, MarketName supermarket) {
        Supermarket toFind = supermarketRepository.findByName(supermarket).orElseThrow(() -> new NoSuchElementException("No se ha encontrado ese supermercado"));
        return repository.findByProduct_EanAndSupermarketOrderBySupermarket(ean, toFind);
    }
}
