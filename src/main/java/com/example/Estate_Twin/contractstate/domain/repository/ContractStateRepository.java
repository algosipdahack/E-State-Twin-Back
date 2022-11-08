package com.example.Estate_Twin.contractstate.domain.repository;

import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractStateRepository extends JpaRepository<ContractState,Long> ,ContractStateRepositoryCustom{
    Optional<ContractState> findContractStateByEstate_Id(Long estateId);
}
