package com.binarybeasts.voyalsuper.repository;

import com.binarybeasts.voyalsuper.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {


}
