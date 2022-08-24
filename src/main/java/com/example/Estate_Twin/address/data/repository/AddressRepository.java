package com.example.Estate_Twin.address.data.repository;

import com.example.Estate_Twin.address.data.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
