package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.BillDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailRepository extends JpaRepository<BillDetailEntity, Long> {

    BillDetailEntity findBillDetailEntityById(Long billDetailCode);



//    Page<BillDetailEntity> findByBillDetailNameContainingOrderByIdAsc(String billDetailName, Pageable pageable);

}
