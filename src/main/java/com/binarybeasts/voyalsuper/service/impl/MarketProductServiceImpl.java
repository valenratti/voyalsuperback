package com.binarybeasts.voyalsuper.service.impl;

import com.binarybeasts.voyalsuper.model.MarketProduct;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.repository.MarketProductRepository;
import com.binarybeasts.voyalsuper.service.MarketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketProductServiceImpl implements MarketProductService {

    @Autowired
    MarketProductRepository repository;

    @Override
    public List<MarketProduct> getMarketProductsByProductId(Long id) {
        return repository.findAllByProduct_Id(id);
    }

    @Override
    public Optional<MarketProduct> getMarketProductByProductIdAndSupermarket(Long id, MarketName supermarket) {
        return repository.findByProduct_IdAndSupermarketOrderBySupermarket(id, supermarket);
    }
}
