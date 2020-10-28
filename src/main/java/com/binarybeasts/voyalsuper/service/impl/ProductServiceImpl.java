package com.binarybeasts.voyalsuper.service.impl;

import com.binarybeasts.voyalsuper.model.Product;
import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.dto.ProductDto;
import com.binarybeasts.voyalsuper.model.dto.ProductFilterDto;
import com.binarybeasts.voyalsuper.model.dto.ProductPageDto;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.model.enums.ProductCategory;
import com.binarybeasts.voyalsuper.repository.ProductFilterRepository;
import com.binarybeasts.voyalsuper.repository.ProductRepository;
import com.binarybeasts.voyalsuper.repository.SupermarketRepository;
import com.binarybeasts.voyalsuper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductFilterRepository productFilterRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SupermarketRepository supermarketRepository;

    @Override
    public Product addProduct(ProductDto product) {
        Product toAdd = new Product(product.getDescription(),product.getCategory(),product.getEan(), product.getImgUrl(), new ArrayList<>());
        return productRepository.save(toAdd);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getProductByEan(String ean) {
        return productRepository.findByEan(ean);
    }

    @Override
    public List<Product> getAllProductsFromCategory(ProductCategory category) {
        return null;
    }

    @Override
    public List<Product> getAllProductsFromCategoryByPage(ProductCategory category, Integer page, Integer size) {
        return productRepository.findAllByCategory(category, PageRequest.of(page,size)).getContent();
    }

    @Override
    public ProductPageDto getFilteredProductsByPage(ProductFilterDto productFilter, Integer page, Integer size, String orderBy, boolean asc) {
        return productFilterRepository.getFilteredProductsByPage(productFilter,page,size,orderBy, asc);
    }

    @Override
    public Product addSupermarketWithStockOfProduct(Product product, MarketName name) {
        Supermarket market = supermarketRepository.findByName(name);
        product.getMarketsWithStock().add(market);
        return productRepository.save(product);
    }


}