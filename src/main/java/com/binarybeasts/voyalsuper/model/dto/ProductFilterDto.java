package com.binarybeasts.voyalsuper.model.dto;

import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.binarybeasts.voyalsuper.model.enums.ProductCategory;

import java.util.List;

public class ProductFilterDto {

    private String name;

    private List<ProductCategory> categories;

    private List<MarketName> supermarkets;

    public ProductFilterDto() {
    }

    public ProductFilterDto(String name, List<ProductCategory> categories, List<MarketName> supermarkets) {
        this.name = name;
        this.categories = categories;
        this.supermarkets = supermarkets;
    }

    public String getName() {
        return name;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public List<MarketName> getSupermarkets() {
        return supermarkets;
    }
}
