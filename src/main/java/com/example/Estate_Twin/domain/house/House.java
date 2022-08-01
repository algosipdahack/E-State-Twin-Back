package com.example.Estate_Twin.domain.house;

import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.domain.asset.Asset;
import com.example.Estate_Twin.domain.estate.Estate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "house")
public class House extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private long id;

    @Column
    private long Deposit;

    @Column
    private long MonthlyRent;

    @Column
    private long SellingFee;

    @Column
    private long CurrentFloors;

    @Column
    private long TotalFloors;

    @Column
    private boolean ShortTermRent;

    @Column
    private long MaintenanceFee;

    @Column
    private String ItemsIncludedMaintenanceFee;

    @Column
    private long NetRentableArea;

    @Column
    private long RentableArea;

    @Column
    private boolean Parking;

    @Column
    private long ParkingFee;

    @Column
    private Date MoveInAvailableDate;

    @Column
    private long Size;

    @Column
    private String HeatType;

    @Column
    private String EstateType;

    @Column
    private long Household;

    @Column
    private Date UsageAvailableDate;

    @Column
    private long RoomCount;

    @Column
    private long BathCount;

    @OneToMany(
            mappedBy = "house",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    private List<Asset> assets = new ArrayList<>();

    @OneToMany(
            mappedBy = "house",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    private List<Estate> estates = new ArrayList<>();
}
