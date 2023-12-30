package com.fpt.duantn.services;

import com.fpt.duantn.io.entity.EmployeeEntity;
import com.fpt.duantn.shrared.dto.CRUD.EmployeeDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employee);
    EmployeeDto getEmployeeById(Long employeeId);
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto employee);
    void deleteEmployee(Long employeeId);
    List<EmployeeDto> getEmployees(int page, int limit, String filter);

    Optional<EmployeeEntity> findById(Long aLong);

    Long count();

    Boolean existsByEmail(String email);

    Optional<EmployeeEntity> findByEmail(String email);

    @Query("SELECT e from  EmployeeEntity e where e.phoneNumber like :phoneNumber")
    Optional<EmployeeEntity> findEByPhoneNumber(String phoneNumber);

    Long count(String filter);
    List<EmployeeDto> getEmployeeByEmployeeName(String employeeName, int page, int limit);


}
