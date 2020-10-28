package com.binarybeasts.voyalsuper.model.dto;

import com.binarybeasts.voyalsuper.model.Product;

import java.util.List;

public class ProductPageDto {

    private final List<Product> products;

    private final long totalRecords;

    public ProductPageDto(List<Product> products, long totalRecords) {
        this.products = products;
        this.totalRecords = totalRecords;
    }

    public List<Product> getProducts() {
        return products;
    }

    public long getTotalRecords() {
        return totalRecords;
    }
}
