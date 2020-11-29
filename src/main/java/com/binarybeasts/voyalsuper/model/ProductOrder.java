package com.binarybeasts.voyalsuper.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Embeddable
public class ProductOrder {

    @ManyToOne
    private MarketProduct product;

    private int quantity;

    public MarketProduct getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProduct(MarketProduct product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
