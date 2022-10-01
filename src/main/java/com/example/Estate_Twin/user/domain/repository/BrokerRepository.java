package com.example.Estate_Twin.user.domain.repository;

import com.example.Estate_Twin.user.domain.entity.Broker;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface BrokerRepository extends JpaRepository<Broker,Long> ,BrokerRepositoryCustom{
    @Query("SELECT b FROM Broker b join b.user u where u.email = :email")
    Optional<Broker> findByUserEmailWithUserUsingJoin(String email);

    @Query("select b from Broker b join fetch b.tradeEstates where b.id = :id")
    Optional<Broker> findByIdUsingFetchJoin(Long id);
}

