package com.binarybeasts.voyalsuper.repository;

import com.binarybeasts.voyalsuper.model.Product;
import com.binarybeasts.voyalsuper.model.dto.ProductFilterDto;
import com.binarybeasts.voyalsuper.model.dto.ProductPageDto;

import java.util.List;

public interface ProductFilterRepository {

    ProductPageDto getFilteredProductsByPage(ProductFilterDto productFilter, Integer page, Integer size, String orderColumn, boolean asc);

    List<Product> getFilteredProducts(ProductFilterDto productFilterDto, String orderColumn, boolean asc);
}
