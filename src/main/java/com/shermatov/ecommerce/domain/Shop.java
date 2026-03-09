package com.shermatov.ecommerce.domain;

import com.shermatov.ecommerce.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "shop")
public class Shop extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Product> products;
}
