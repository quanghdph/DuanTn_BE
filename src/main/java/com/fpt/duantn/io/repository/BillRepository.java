package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.BillEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {

    BillEntity findBillEntityById(Long billId);


//    Page<BillEntity> findByCustomerNameContainingOrderByIdAsc(String billName, Pageable pageable);

    @Query(value = "SELECT bil.id, bil.customer_id," +
            "bil.employee_id,bil.create_date,bil.payment_date,bil.delivery_date,bil.update_date, " +
            "bil.status,bil.address,bil.phone_number,bil.transport_fee,bil.note " +
            "FROM bill bil " +
            "where 1=1 and (:filter is null or :filter = '' or (bil.customer_id like %:filter% or bil.status like %:filter% or bil.employee_id like %:filter%))",
            nativeQuery = true)
    Page<BillEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM bill bil " +
            "where 1=1 and (:filter is null or :filter = '' or (bil.customer_id like %:filter% or bil.status like %:filter% or bil.employee_id like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}
