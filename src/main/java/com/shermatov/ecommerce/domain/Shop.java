package com.shermatov.ecommerce.domain;

import com.shermatov.ecommerce.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shop extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;
}
