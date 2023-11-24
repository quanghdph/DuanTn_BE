package com.fpt.duantn.services.impl;

import com.fpt.duantn.io.entity.EmployeeEntity;
import com.fpt.duantn.io.repository.EmployeeRepository;
import com.fpt.duantn.services.EmployeeCardService;
import com.fpt.duantn.shrared.dto.CRUD.EmployeeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeCardServiceimpl implements EmployeeCardService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getEmployeeCard(int page, int limit) {
        List<EmployeeDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<EmployeeEntity> employeeCardPage = employeeRepository.getEmployees(pageableRequest);
        List<EmployeeEntity> employeeCart = employeeCardPage.getContent();

        for (EmployeeEntity employeeCartEntity : employeeCart) {
            EmployeeDto employeeDto = new EmployeeDto();
            BeanUtils.copyProperties(employeeCartEntity, employeeDto);
            returnValue.add(employeeDto);
        }

        return returnValue;
    }
}
