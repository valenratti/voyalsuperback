package com.binarybeasts.voyalsuper.model.dto;

public class ProductOrderDto {

    private String ean;

    private int quantity;

    public ProductOrderDto() {
    }

    public ProductOrderDto(String ean, int quantity) {
        this.ean = ean;
        this.quantity = quantity;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
