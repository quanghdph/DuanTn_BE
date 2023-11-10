package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employee);
    EmployeeDto getEmployeeByEmployeeCode(String employeeCode);
    EmployeeDto updateEmployee(String employeeCode, EmployeeDto employee);
    void deleteEmployee(String employeeCode);
    List<EmployeeDto> getEmployees(int page, int limit, String filter);
    Long count(String filter);
    List<EmployeeDto> getEmployeeByEmployeeName(String employeeName, int page, int limit);


}
