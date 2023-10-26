package com.fpt.duantn.io.repository;

import com.fpt.duantn.io.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    EmployeeEntity findByEmployeeCode(String employeeCode);

    Page<EmployeeEntity> findByLastNameContainingOrderByIdAsc(String employeeName, Pageable pageable);

}
