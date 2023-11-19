package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CustomerEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByCustomerCode(String customerCode);

    CustomerEntity findCustomerEntityById(Long customerId);


    Page<CustomerEntity> findByLastNameContainingOrderByIdAsc(String customerName, Pageable pageable);

    @Query(value = "SELECT cust.id, cust.email_verification_status," +
            "cust.first_name,cust.last_name,cust.email,cust.email_verification_token,cust.create_date,cust.update_date, " +
            "cust.encrypted_password,cust.gender,cust.dateof_birth,cust.phone_number,cust.customer_code " +
            "FROM customers cust " +
            "where 1=1 and (:filter is null or :filter = '' or (cust.last_name like %:filter% or cust.create_date like %:filter% or cust.customer_code like %:filter%))",
            nativeQuery = true)
    Page<CustomerEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM customers cust " +
            "where 1=1 and (:filter is null or :filter = '' or (cust.last_name like %:filter% or cust.create_date like %:filter% or cust.customer_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}
