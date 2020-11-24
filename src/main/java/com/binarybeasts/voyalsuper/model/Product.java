package com.binarybeasts.voyalsuper.model;


import com.binarybeasts.voyalsuper.model.enums.ProductCategory;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    private String ean;

    @NotBlank
    private String description;

    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private ProductCategory category;

    @NotBlank
    private String imgUrl;

    @ManyToMany
    private Set<Supermarket> marketsWithStock;

    public Product() {
        this.marketsWithStock = new HashSet<>();
    }

    public Product(@NotBlank String description, @NotBlank ProductCategory category, @NotBlank String ean, @NotBlank String imgUrl) {
        this.description = description;
        this.category = category;
        this.ean = ean;
        this.imgUrl = imgUrl;
        this.marketsWithStock = new HashSet<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Supermarket> getMarketsWithStock() {
        return marketsWithStock;
    }
}
