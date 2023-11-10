package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.EmployeeEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    EmployeeEntity findByEmployeeCode(String employeeCode);

    Page<EmployeeEntity> findByLastNameContainingOrderByIdAsc(String employeeName, Pageable pageable);


    @Query(value = "SELECT e.id, e.product_name," +
            "e.category_id,e.brand_id,e.main_image,e.description,e.create_date,e.update_date, " +
            "e.status,e.product_code,e.quantity,e.price " +
            "FROM employees e " +
            "where 1=1 and (:filter is null or :filter = '' or (e.product_name like %:filter% or e.status like %:filter% or e.product_code like %:filter%))",
            nativeQuery = true)
    Page<EmployeeEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM products e " +
            "where 1=1 and (:filter is null or :filter = '' or (e.product_name like %:filter% or e.status like %:filter% or e.product_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}
