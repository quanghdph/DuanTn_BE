package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.BillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<BillEntity, Long> {

    BillEntity findByBillCode(String billCode);

    Page<BillEntity> findByCustomerNameContainingOrderByIdAsc(String billName, Pageable pageable);

}
