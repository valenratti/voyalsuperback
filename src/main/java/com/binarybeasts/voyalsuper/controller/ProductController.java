package com.binarybeasts.voyalsuper.controller;

import com.binarybeasts.voyalsuper.model.Product;
import com.binarybeasts.voyalsuper.model.dto.ProductDto;
import com.binarybeasts.voyalsuper.model.dto.ProductFilterDto;
import com.binarybeasts.voyalsuper.model.dto.ProductPageDto;
import com.binarybeasts.voyalsuper.model.enums.ProductCategory;
import com.binarybeasts.voyalsuper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductDto product){
        return productService.addProduct(product);
    }

    @PostMapping("/filter")
    public ProductPageDto getFilteredProductsByPage(@RequestBody ProductFilterDto filterDto, @RequestParam Integer page, @RequestParam Integer size, @RequestParam String orderBy, @RequestParam boolean asc){
        return productService.getFilteredProductsByPage(filterDto,page,size,orderBy,asc);
    }

    @GetMapping("/get/{category}")
    public List<Product> getProductsByCategory(@PathVariable ProductCategory category){
        return productService.getAllProductsFromCategory(category);
    }
}
