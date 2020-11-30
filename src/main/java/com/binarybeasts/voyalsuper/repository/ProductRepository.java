package com.binarybeasts.voyalsuper.repository;


import com.binarybeasts.voyalsuper.model.Product;
import com.binarybeasts.voyalsuper.model.enums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByDescription(String name);

    Optional<Product> findByEan(String ean);

    void deleteByEan(String ean);

    List<Product> findAllByCategory(ProductCategory category);

    Page<Product> findAllByCategory(ProductCategory category, Pageable pageable);

}
