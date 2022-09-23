package com.example.Estate_Twin.user.domain.repository;

import com.example.Estate_Twin.user.domain.entity.Broker;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface BrokerRepository extends JpaRepository<Broker,Long> {
    @Query("SELECT b FROM Broker b join b.user u where u.email = :email")
    Optional<Broker> findByUserEmailWithUserUsingJoin(String email);
}
