package com.binarybeasts.voyalsuper.model.dto;

import com.binarybeasts.voyalsuper.model.enums.MarketName;

public class MarketProductDto {

    private String ean;

    private Double price;

    private String url;

    private MarketName supermarket;

    public MarketProductDto() {
    }

    public MarketProductDto(String ean, Double price, String url, MarketName supermarket) {
        this.ean = ean;
        this.price = price;
        this.url = url;
        this.supermarket = supermarket;
    }

    public String getEan() {
        return ean;
    }

    public Double getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public MarketName getSupermarket() {
        return supermarket;
    }
}
