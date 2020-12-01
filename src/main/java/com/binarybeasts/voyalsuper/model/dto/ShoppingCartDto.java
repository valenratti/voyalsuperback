package com.binarybeasts.voyalsuper.model.dto;

import com.binarybeasts.voyalsuper.model.ProductOrder;

import java.util.List;

public class ShoppingCartDto {

    private List<ProductOrderDto> productList;

    public ShoppingCartDto() {
    }

    public ShoppingCartDto(List<ProductOrderDto> productList) {
        this.productList = productList;
    }

    public List<ProductOrderDto> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductOrderDto> productList) {
        this.productList = productList;
    }
}
