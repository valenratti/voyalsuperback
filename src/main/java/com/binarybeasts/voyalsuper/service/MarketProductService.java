package com.binarybeasts.voyalsuper.service;

import com.binarybeasts.voyalsuper.model.MarketProduct;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.model.enums.ProductCategory;


import java.util.List;
import java.util.Optional;

public interface MarketProductService {

    List<MarketProduct> getMarketProductsByProductId(Long id);

    Optional<MarketProduct> getMarketProductByProductIdAndSupermarket(Long id, MarketName supermarket);

}
