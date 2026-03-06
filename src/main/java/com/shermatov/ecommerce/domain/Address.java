package com.shermatov.ecommerce.domain;

import com.shermatov.ecommerce.domain.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String country;
    private String city;
    private String street;
    private String postalCode;
}
