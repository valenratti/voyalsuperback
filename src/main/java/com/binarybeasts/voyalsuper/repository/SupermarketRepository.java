package com.binarybeasts.voyalsuper.repository;

import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {

    Supermarket findByName(MarketName name);

    List<Supermarket> findAllByNameIn(List<MarketName> supermarkets);
}
