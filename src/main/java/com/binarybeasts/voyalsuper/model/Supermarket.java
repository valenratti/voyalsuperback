package com.binarybeasts.voyalsuper.model;

import com.binarybeasts.voyalsuper.model.enums.MarketName;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Supermarket {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private MarketName name;

    @OneToMany
    @JoinTable(name = "market_products", joinColumns = @JoinColumn(name = "market_id", referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "market_product_id", referencedColumnName = "id"))
    private List<MarketProduct> productList;

    public Supermarket() {
    }

    public Supermarket(@NotBlank MarketName name, List<MarketProduct> productList) {
        this.name = name;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MarketName getName() {
        return name;
    }

    public void setName(MarketName name) {
        this.name = name;
    }

    public List<MarketProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<MarketProduct> productList) {
        this.productList = productList;
    }
}
