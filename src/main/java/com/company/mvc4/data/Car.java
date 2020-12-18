package com.company.mvc4.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="car")
public class Car {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "number")
    private String number;
    @Column(name = "vin_number")
    private String vinNumber;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;
}
