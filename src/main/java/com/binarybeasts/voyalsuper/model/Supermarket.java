package com.binarybeasts.voyalsuper.model;

import com.binarybeasts.voyalsuper.model.enums.MarketName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Supermarket {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private MarketName name;

    @OneToMany
    @JsonIgnore
    @JoinTable(name = "market_products", joinColumns = @JoinColumn(name = "market_id", referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "market_product_id", referencedColumnName = "id"))
    private Set<MarketProduct> productList;

    public Supermarket() {
        this.productList = new HashSet<>();
    }

    public Supermarket(@NotBlank MarketName name) {
        this.name = name;
        this.productList = productList;
        this.productList = new HashSet<>();
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

    public Set<MarketProduct> getProductList() {
        return productList;
    }

    public void setProductList(Set<MarketProduct> productList) {
        this.productList = productList;
    }

    public void addProduct(MarketProduct product){
        productList.add(product);
    }
}
