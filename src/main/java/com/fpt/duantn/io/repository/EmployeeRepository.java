package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.EmployeeEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    EmployeeEntity findByEmployeeCode(String employeeCode);

    EmployeeEntity findEmployeeEntityById(Long employeeId);


    Page<EmployeeEntity> findByLastNameContainingOrderByIdAsc(String employeeName, Pageable pageable);


    @Query(value = "SELECT e.id, e.first_name," +
            "e.last_name,e.gender,e.dateof_birth,e.email,e.create_date,e.update_date, " +
            "e.status,e.phone_number,e.encrypted_password, e.image, e.employee_code " +
            "FROM employees e " +
            "where 1=1 and (:filter is null or :filter = '' or (e.last_name like %:filter% or e.status like %:filter% or e.employee_code like %:filter%))",
            nativeQuery = true)
    Page<EmployeeEntity> filter(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT count(1) " +
            "FROM employees e " +
            "where 1=1 and (:filter is null or :filter = '' or (e.last_name like %:filter% or e.status like %:filter% or e.employee_code like %:filter%))",
            nativeQuery = true)
    Long count(@Param("filter") String filter);
}
