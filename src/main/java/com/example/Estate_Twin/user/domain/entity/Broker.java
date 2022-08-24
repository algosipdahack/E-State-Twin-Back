package com.example.Estate_Twin.user.domain.entity;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "broker")
@AttributeOverride(name = "id", column = @Column(name = "BROKER_ID"))
public class Broker extends BaseEntity {
    // broker column
    @Column
    private String brokerPhoto;

    @Column
    private String businessName;

    @Column
    private String agentName;

    @Column
    private String brokerageRegistrationNumber;

    @Column
    private String businessRegistrationNumber;

    @Column
    private String businessLicense;

    @Column
    private String brokerageRegistrationLicense;

    @OneToOne(mappedBy = "estate")
    private Address broker_address;

    @OneToMany(
            mappedBy = "broker",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    private List<Estate> estates = new ArrayList<>();

}
