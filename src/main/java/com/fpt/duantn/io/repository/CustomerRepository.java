package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByCustomerCode(String customerCode);

    Page<CustomerEntity> findByLastNameContainingOrderByIdAsc(String customerName, Pageable pageable);

}
