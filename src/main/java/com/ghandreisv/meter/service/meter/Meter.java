package com.ghandreisv.meter.service.meter;

import com.ghandreisv.meter.service.address.Address;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "METERS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString(exclude="address")
public class Meter {
    public static final String ENTITY_TYPE = "Meter";

    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
}
