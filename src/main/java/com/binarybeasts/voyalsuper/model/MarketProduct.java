package com.binarybeasts.voyalsuper.model;

import com.binarybeasts.voyalsuper.model.enums.MarketName;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "market_product")
public class MarketProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @NotBlank
    private Double price;

    @NotBlank
    private String url;

    @ManyToOne
    private Supermarket supermarket;

    public MarketProduct() {
    }

    public MarketProduct(@NotBlank Product product, @NotBlank Double price, @NotBlank String url, @NotBlank Supermarket supermarket) {
        this.product = product;
        this.price = price;
        this.url = url;
        this.supermarket = supermarket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Supermarket getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }
}
