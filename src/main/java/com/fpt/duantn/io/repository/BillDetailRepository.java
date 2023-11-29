package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.BillDetailEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


@Repository
public interface BillDetailRepository extends JpaRepository<BillDetailEntity, Long> {

    BillDetailEntity findBillDetailEntityById(Long billDetailId);


//    Page<BillDetailEntity> findByBillDetailNameContainingOrderByIdAsc(String billDetailName, Pageable pageable);

    @Query(value = "SELECT bild.id, bild.bill_id," +
            "bild.product_detail_id,bild.quantity,bild.default_price, " +
            "bild.status,bild.create_date,bild.update_time,bild.price " +
            "FROM bill_detail bild " +
            "where 1=1 and (:filter is null or :filter = '' or (bild.bill_id like %:filter% or bild.status like %:filter% or bild.create_date like %:filter% or bild.update_time like %:filter%))",
            nativeQuery = true)
    Page<BillDetailEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM bill_detail bild " +
            "where 1=1 and (:filter is null or :filter = '' or (bild.bill_id like %:filter% or bild.status like %:filter% or bild.create_date like %:filter% or bild.update_time like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}
