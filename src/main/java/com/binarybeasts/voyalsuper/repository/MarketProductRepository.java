package com.binarybeasts.voyalsuper.repository;

import com.binarybeasts.voyalsuper.model.MarketProduct;
import com.binarybeasts.voyalsuper.model.Product;
import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

public interface MarketProductRepository extends JpaRepository<MarketProduct, Long> {

    Optional<MarketProduct> findByProduct_IdAndSupermarketOrderBySupermarket(Long id, MarketName supermarket);

    List<MarketProduct> findAllByProduct_Id(Long id);

}
