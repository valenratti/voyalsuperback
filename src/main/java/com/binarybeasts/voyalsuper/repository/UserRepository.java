package com.binarybeasts.voyalsuper.repository;

import com.binarybeasts.voyalsuper.model.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DAOUser, Long> {

    Optional<DAOUser> findByUsername(String username);

    Optional<DAOUser> findById(Long id);

    boolean existsByUsername(String username);

    Optional<DAOUser> findByEmail(String email);
}
