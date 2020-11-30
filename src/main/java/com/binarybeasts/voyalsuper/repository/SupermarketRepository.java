package com.binarybeasts.voyalsuper.repository;

import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.enums.MarketName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {

    Optional<Supermarket> findByName(MarketName name);

    List<Supermarket> findAllByNameIn(List<MarketName> supermarkets);
}
