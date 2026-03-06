package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
