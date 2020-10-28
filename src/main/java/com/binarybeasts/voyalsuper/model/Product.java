package com.binarybeasts.voyalsuper.model;


import com.binarybeasts.voyalsuper.model.enums.ProductCategory;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;

    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private ProductCategory category;

    @NotBlank
    private String ean;

    @NotBlank
    private String imgUrl;

    @ManyToMany
    private List<Supermarket> marketsWithStock;

    public Product() {
    }

    public Product(@NotBlank String description, @NotBlank ProductCategory category, @NotBlank String ean, @NotBlank String imgUrl, List<Supermarket> marketsWithStock) {
        this.description = description;
        this.category = category;
        this.ean = ean;
        this.imgUrl = imgUrl;
        this.marketsWithStock = marketsWithStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Supermarket> getMarketsWithStock() {
        return marketsWithStock;
    }
}
