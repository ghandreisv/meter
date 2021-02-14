package com.ghandreisv.meter.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESSES")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString()
public class Address {
    public static final String ENTITY_TYPE = "Address";

    @Id
    private String id;
    private String street;
    private String houseNumber;
    private String city;
    private String country;
}
