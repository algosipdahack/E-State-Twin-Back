package com.example.Estate_Twin.domain.filter;

import com.example.Estate_Twin.domain.BaseTimeEntity;
import com.example.Estate_Twin.domain.estate.EstateStructure;
import com.example.Estate_Twin.domain.estate.EstateType;
import com.example.Estate_Twin.domain.estate.Rank;
import com.example.Estate_Twin.domain.estate.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "filter")
public class Filter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filter_id")
    private long id;

    @Enumerated(EnumType.STRING)
    private EstateType estateType;

    @ElementCollection
    @CollectionTable(name = "transaction_type", joinColumns = @JoinColumn(name = "filter_id"))
    private List<TransactionType> transactionType;

    @Column
    private int currentFloor;

    @Enumerated(EnumType.STRING)
    private EstateStructure estateStructure;

    @Column
    private int usageAvailableDate;

    @Column
    private int vehNumber;

    @Column
    private int houseHold;

    @Embedded
    private Range monthlyRentFee;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="max", column=@Column(name = "sellingFee_max")),
            @AttributeOverride(name="min", column=@Column(name = "sellingFee_min")),
    })
    private Range sellingFee;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="max", column=@Column(name = "maintenanceFee_max")),
            @AttributeOverride(name="min", column=@Column(name = "maintenanceFee_min")),
    })
    private Range maintenanceFee;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="max", column=@Column(name = "size_max")),
            @AttributeOverride(name="min", column=@Column(name = "size_min")),
    })
    private Range size;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="max", column=@Column(name = "deposit_max")),
            @AttributeOverride(name="min", column=@Column(name = "deposit_min")),
    })
    private Range deposit;

    @ElementCollection
    @CollectionTable(name = "rank", joinColumns = @JoinColumn(name = "filter_id"))
    private List<Rank> rank;

}
