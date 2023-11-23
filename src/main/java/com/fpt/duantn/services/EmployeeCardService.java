package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.EmployeeDto;

import java.util.List;

public interface EmployeeCardService {

    List<EmployeeDto> getEmployeeCard(int page, int limit);

}
