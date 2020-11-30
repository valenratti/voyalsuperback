package com.binarybeasts.voyalsuper.repository;

import com.binarybeasts.voyalsuper.model.MarketProduct;
import com.binarybeasts.voyalsuper.model.Product;
import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface MarketProductRepository extends JpaRepository<MarketProduct, Long> {

    Optional<MarketProduct> findByProduct_EanAndSupermarketOrderBySupermarket(String ean, Supermarket supermarket);

    List<MarketProduct> findAllByProduct_Ean(String ean);

}
