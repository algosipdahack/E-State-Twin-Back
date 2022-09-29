package com.example.Estate_Twin.user.domain.repository;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.user.domain.entity.Broker;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

public interface BrokerRepository extends JpaRepository<Broker,Long> ,BrokerRepositoryCustom{
    @Query("SELECT b FROM Broker b join b.user u where u.email = :email")
    Optional<Broker> findByUserEmailWithUserUsingJoin(String email);
    //TODO 검증
    /*@Query("select e.id, u.name, u.phone from Estate e join fetch e.owner u where e.state = :state and e.broker.id = :brokerId")
    List<BrokerEstateDto> findAllWithEstateByState(Long brokerId, State state);
    */
}

