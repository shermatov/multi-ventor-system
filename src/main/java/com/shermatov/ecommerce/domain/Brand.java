package com.shermatov.ecommerce.domain;

import com.shermatov.ecommerce.domain.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Brand extends BaseEntity {

    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
