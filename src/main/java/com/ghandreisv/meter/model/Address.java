package com.ghandreisv.meter.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString(exclude = "meter")
public class Address {
    public static final String ENTITY_TYPE = "Address";

    @Id
    private String id;
    private String street;
    private String houseNumber;
    private String city;
    private String country;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "ADDRESSES_METERS",
            joinColumns = { @JoinColumn(name = "address_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "meter_id", referencedColumnName = "id")}
    )
    private Meter meter;
}
