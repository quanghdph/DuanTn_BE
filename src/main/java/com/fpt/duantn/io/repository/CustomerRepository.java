package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.CustomerEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.ui.model.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(value = "SELECT c.id, c.first_name, c.last_name, c.email, c.gender, c.dateof_birth, c.phone_number, c. customer_code, " +
            "e.id AS employee_id, e.employee_code " +
            "FROM customers c " +
            "JOIN bill b ON c.id = b.customer_id " +
            "JOIN employees e ON b.employee_id = e.id", nativeQuery = true)

    Page<CustomerEntity> getCustomersAndBills(Pageable pageable);


    CustomerEntity findByCustomerCode(String customerCode);

    CustomerEntity findCustomerEntityById(Long customerId);


    Page<CustomerEntity> findByLastNameContainingOrderByIdAsc(String customerName, Pageable pageable);

    @Query(value = "SELECT cust.id, cust.email_verification_status," +
            "cust.first_name,cust.last_name,cust.email,cust.email_verification_token,cust.create_date,cust.update_date, " +
            "cust.encrypted_password,cust.gender,cust.dateof_birth,cust.phone_number,cust.customer_code " +
            "FROM customers cust " +
            "where 1=1 and (:filter is null or :filter = '' or (cust.last_name like %:filter%  or cust.customer_code like %:filter%))",
            nativeQuery = true)
    Page<CustomerEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM customers cust " +
            "where 1=1 and (:filter is null or :filter = '' or (cust.last_name like %:filter%  or cust.customer_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);

    @Query("SELECT c from  CustomerEntity c where ( c.phoneNumber like :phoneNumber) " )
    public Optional<CustomerEntity> findCByPhoneNumber(String phoneNumber);

    @Query("SELECT new com.fpt.duantn.ui.model.response.CustomerResponse(c.id,c.firstName,c.lastName,c.email,c.gender,c.dateOfBirth,c.phoneNumber,c.createDate,c.updateDate,c.customerCode) from  CustomerEntity c where c.phoneNumber like :phoneNumber" )
    public CustomerResponse findByPhoneNumber(String phoneNumber);
    public Optional<CustomerEntity> findByEmail(String email);

    public Boolean existsByEmail(String email);


}
