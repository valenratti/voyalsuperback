package com.binarybeasts.voyalsuper.vtexapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VtexResponse {
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("productReference")
    private Long ean;
    @JsonProperty("link")
    private String link;
    //private String imageUrl;
    private Double listPrice;

    public VtexResponse(){}

    @JsonProperty("productName")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("productReference")
    public void setEan(Long ean) {
        this.ean = ean;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    @JsonProperty("productName")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("productReference")
    public Long getEan() {
        return ean;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    public Double getListPrice() {
        return listPrice;
    }

    @JsonProperty("items")
    public void setItems(List<Item> items){
        if(items.size()!=0) {
            List<Seller> sellers = items.get(0).getSellers();
            CommertialOffer commertialOffers = sellers.get(0).getCommertialOffer();
            this.listPrice = commertialOffers.getListPrice();
        }
    }

    @Override
    public String toString() {
        return String.format("Product name:%s\nEAN:%d\nLink:%s\nList Price:%.2f",getProductName(),getEan(),getLink(),getListPrice());
    }

    private static class Item {

        private List<Seller> sellers = null;

        public Item(){}

        @JsonProperty("sellers")
        public List<Seller> getSellers() {
            return sellers;
        }

        @JsonProperty("sellers")
        public void setSellers(List<Seller> sellers) {
            this.sellers = sellers;
        }

    }

    private static class Seller{
        private CommertialOffer commertialOffer;

        public Seller(){}

        @JsonProperty("commertialOffer")
        public CommertialOffer getCommertialOffer() {
            return commertialOffer;
        }

        @JsonProperty("commertialOffer")
        public void setCommertialOffer(CommertialOffer commertialOffer) {
            this.commertialOffer = commertialOffer;
        }
    }

    private static class CommertialOffer{
        private Double price;
        private Double listPrice;

        public CommertialOffer(){}

        @JsonProperty("Price")
        public Double getPrice() {
            return price;
        }

        @JsonProperty("Price")
        public void setPrice(Double price) {
            this.price = price;
        }

        @JsonProperty("ListPrice")
        public Double getListPrice() {
            return listPrice;
        }

        @JsonProperty("ListPrice")
        public void setListPrice(Double listPrice) {
            this.listPrice = listPrice;
        }
    }
}
