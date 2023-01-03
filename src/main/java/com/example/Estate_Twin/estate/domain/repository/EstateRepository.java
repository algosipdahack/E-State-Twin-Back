package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.user.domain.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface EstateRepository extends JpaRepository<Estate,Long>, EstateRepositoryCustom {
    @Query("select new com.example.Estate_Twin.estate.web.dto.BrokerEstateDto(e.id, u.name, u.phone, e.address) " +
            "from Estate e " +
            "inner join User u ON e.owner.id = u.id " +
            "inner join Broker b ON e.broker.id = b.id where b.user.id = u.id and u.email = :email and e.broker.id = b.id and e.state = :state")
    List<BrokerEstateDto> findEstateByEmailAndState(@Param("email") String email, @Param("state") State state);

    @Query("select e.broker from Estate e where e.id = :estateId")
    Optional<Broker> findBrokerByEstate_Id(@Param("estateId") Long estateId);
    Optional<List<Estate>> findEstatesByBroker_Id(Long brokerId);
    Optional<Estate> findEstateByHouse_Id(Long houseId);
    Optional<Estate> findEstateByEstateHit_Id(Long estatehitId);
    Optional<List<Estate>> findEstatesByOwner_Id(Long ownerId);
}
