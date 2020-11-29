package com.binarybeasts.voyalsuper.model.dto;

import com.binarybeasts.voyalsuper.model.enums.ProductCategory;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

public class ProductDto {

    private String description;

    private ProductCategory category;

    private String ean;

    private String imgUrl;

    public ProductDto(String description, ProductCategory category, String ean, String imgUrl) {
        this.description = description;
        this.category = category;
        this.ean = ean;
        this.imgUrl = imgUrl;
    }

    public ProductDto() {
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public String getEan() {
        return ean;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}

