package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.BillEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {

    BillEntity findBillEntityById(Long billId);


//    Page<BillEntity> findByCustomerNameContainingOrderByIdAsc(String billName, Pageable pageable);

    @Query(value = "SELECT bil.id, bil.customer_id," +
            "bil.employee_id,bil.create_date,bil.payment_date,bil.delivery_date,bil.update_date, " +
            "bil.status,bil.address,bil.phone_number,bil.transport_fee,bil.note,bil.payment_type,bil.payment_amount,bil.shipe_fee " +
            "FROM bill bil " +
            "where  (:status = -1 or bil.status = :status) and (:filter is null or :filter = '' or (bil.phone_number like %:filter% ))",
            nativeQuery = true)
    Page<BillEntity> filter(@Param("filter") String filter,int status, Pageable pageable);


    @Query(value = "SELECT bil.id, bil.customer_id," +
            "bil.employee_id,bil.create_date,bil.payment_date,bil.delivery_date,bil.update_date, " +
            "bil.status,bil.address,bil.phone_number,bil.transport_fee,bil.note,bil.payment_type,bil.payment_amount,bil.shipe_fee " +
            "FROM bill bil " +
            "where  (:status = -1 or bil.status = :status) and ( bil.customer_id = :userId) and (:filter is null or :filter = '' or (bil.phone_number like %:filter% ))",
            nativeQuery = true)
    Page<BillEntity> filter(@Param("filter") String filter,int status, @Param("userId") Long userId ,Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM bill bil " +
            "where  (:status = -1 or bil.status = :status) and (:filter is null or :filter = '' or (bil.phone_number like %:filter% ))",
            nativeQuery = true)
    Long count(@Param("filter") String filter,int status);

    @Query(value = "SELECT count(1) " +
            "FROM bill bil " +
            "where  (:status = -1 or bil.status = :status) and ( bil.customer_id = :userId) and (:filter is null or :filter = '' or (bil.phone_number like %:filter% ))",
            nativeQuery = true)
    Long count(@Param("filter") String filter,@Param("userId") Long userId,int status);


    public List<BillEntity> findByPaymentTypeAndStatusAndCreateDateBefore(Integer paymentType, Integer status, LocalDateTime billCreateDate);
}
