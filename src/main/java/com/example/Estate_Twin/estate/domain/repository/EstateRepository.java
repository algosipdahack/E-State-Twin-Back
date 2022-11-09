package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import com.example.Estate_Twin.user.domain.entity.Broker;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstateRepository extends JpaRepository<Estate,Long>, EstateRepositoryCustom {
    @Query("select new com.example.Estate_Twin.estate.web.dto.BrokerEstateDto(e.id, u.name, u.phone, e.address) " +
            "from Estate e " +
            "inner join User u ON e.owner.id = u.id " +
            "inner join Broker b ON e.broker.id = b.id where b.id = :brokerId and e.state = :state")
    List<BrokerEstateDto> findEstateBybrokerIdAndState(@Param("brokerId") Long brokerId, @Param("state") State state);

    @Query("select e.broker from Estate e where e.id = :estateId")
    Optional<Broker> findBrokerByEstate_Id(@Param("estateId") Long estateId);

    @Query("select new com.example.Estate_Twin.estate.web.dto.EstateListResponseDto(e.id, e.transactionType, e.estateThumbNail, e.address.town, h.estateType, e.address.buildingName, h.currentFloors, h.rentableArea, e.state, h.sellingFee) " +
            "from Estate e inner join House h ON e.house.id = h.id where e.address.borough = :borough and e.isPosted = true")
    List<EstateListResponseDto> findEstateByBorough(@Param("borough") String borough, Pageable pageable);

    @Query("select new com.example.Estate_Twin.estate.web.dto.EstateListResponseDto(e.id, e.transactionType, e.estateThumbNail, e.address.town, h.estateType, e.address.buildingName, h.currentFloors, h.rentableArea, e.state, h.sellingFee) " +
            "from Estate e inner join House h ON e.house.id = h.id where e.address.town = :town and e.isPosted = true")
    List<EstateListResponseDto> findEstateByTown(@Param("town") String town, Pageable pageable);
    Optional<List<Estate>> findEstatesByBroker_Id(Long brokerId);
    Optional<Estate> findEstateByHouse_Id(Long houseId);
    Optional<Estate> findEstateByEstateHit_Id(Long estatehitId);
    Optional<List<Estate>> findEstatesByOwner_Id(Long ownerId);

}
