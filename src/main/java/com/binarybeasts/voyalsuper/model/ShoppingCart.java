package com.binarybeasts.voyalsuper.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ElementCollection
    @CollectionTable(name = "shopping_cart_products", joinColumns = @JoinColumn(name = "shopping_cart_id"))
    private List<ProductOrder> products;

    @ManyToOne
    private DAOUser owner;

    public ShoppingCart(List<ProductOrder> products) {
        this.products = products;
    }

    public ShoppingCart() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProductOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrder> products) {
        this.products = products;
    }

    public DAOUser getOwner() {
        return owner;
    }

    public void setOwner(DAOUser owner) {
        this.owner = owner;
    }
}
