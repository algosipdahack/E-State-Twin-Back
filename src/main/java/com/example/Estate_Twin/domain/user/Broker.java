package com.example.Estate_Twin.domain.user;

import com.example.Estate_Twin.domain.estate.Estate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String Phone;

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
