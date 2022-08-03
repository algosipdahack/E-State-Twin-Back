package com.example.Estate_Twin.user.domain;

import com.example.Estate_Twin.estate.domain.Estate;
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
    private String BrokerPhoto;

    @Column
    private String BusinessName;

    @Column
    private String AgentName;

    @Column
    private String BrokerageRegistrationNumber;

    @Column
    private String BusinessRegistrationNumber;

    @Column
    private String BusinessLicense;

    @Column
    private String BrokerageRegistrationLicense;

    @Column
    private String Broker_address;

    @OneToMany(
            mappedBy = "broker",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    private List<Estate> estates = new ArrayList<>();

}
