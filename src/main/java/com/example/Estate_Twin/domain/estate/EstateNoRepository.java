package com.example.Estate_Twin.domain.estate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstateNoRepository  extends JpaRepository<EstateNo,Long> {
    public void generateEstateNumber(){

    }
}
