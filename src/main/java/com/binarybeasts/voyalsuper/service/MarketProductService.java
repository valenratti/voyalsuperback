package com.binarybeasts.voyalsuper.service;

import com.binarybeasts.voyalsuper.model.MarketProduct;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.model.enums.ProductCategory;


import java.util.List;
import java.util.Optional;

public interface MarketProductService {

    List<MarketProduct> getMarketProductsByProductEan(String ean);

    Optional<MarketProduct> getMarketProductByProductEanAndSupermarket(String ean, MarketName supermarket);

}
