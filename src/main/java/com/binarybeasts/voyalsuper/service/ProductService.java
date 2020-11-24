package com.binarybeasts.voyalsuper.service;

import com.binarybeasts.voyalsuper.model.Product;
import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.dto.ProductDto;
import com.binarybeasts.voyalsuper.model.dto.ProductFilterDto;
import com.binarybeasts.voyalsuper.model.dto.ProductPageDto;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.model.enums.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product addProduct(ProductDto product);

    void deleteByEan(String ean);

    Optional<Product> getProductByEan(String ean);

    List<Product> getAllProductsFromCategory(ProductCategory category);

    List<Product> getAllProductsFromCategoryByPage(ProductCategory category, Integer page, Integer size);

    ProductPageDto getFilteredProductsByPage(ProductFilterDto productFilter, Integer page, Integer size, String orderBy, boolean asc);

    Product addSupermarketWithStockOfProduct(Product product, MarketName name);
}
