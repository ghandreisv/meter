package com.ghandreisv.meter.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CLIENTS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class Client {
    public static final String ENTITY_TYPE = "Client";

    @Id
    private String id;
    private String name;
    private String surname;
    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

}
