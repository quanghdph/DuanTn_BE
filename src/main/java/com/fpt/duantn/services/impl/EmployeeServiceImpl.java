package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.EmployeeServiceException;
import com.fpt.duantn.io.entity.EmployeeEntity;
import com.fpt.duantn.io.repository.EmployeeRepository;
import com.fpt.duantn.services.EmployeeService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.EmployeeDto;
import com.fpt.duantn.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    Utils utils;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employee) {
        // Kiểm tra xem EmployeeCode đã tồn tại hay chưa
        if (employeeRepository.findByEmployeeCode(employee.getEmployeeCode()) != null) {
            throw new EmployeeServiceException("Employee with the same code already exists");
        }

        // Chuyển đổi EmployeeDto thành EmployeeEntity
        ModelMapper modelMapper = new ModelMapper();
        EmployeeEntity employeeEntity = modelMapper.map(employee, EmployeeEntity.class);

        // Tạo một mã ngẫu nhiên cho EmployeeCode (tùy theo yêu cầu)
        String publicEmployeeCode = utils.generateColorCode(8);
        employeeEntity.setEmployeeCode(publicEmployeeCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        EmployeeEntity storedEmployeeDetails = employeeRepository.save(employeeEntity);

        // Chuyển đổi EmployeeEntity thành EmployeeDto
        EmployeeDto returnValue = modelMapper.map(storedEmployeeDetails, EmployeeDto.class);

        return returnValue;
    }



    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        EmployeeDto returnValue = new EmployeeDto();
        EmployeeEntity employeeEntity = employeeRepository.findEmployeeEntityById(employeeId);

        if (employeeEntity == null)
            throw new EmployeeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(employeeEntity, returnValue);

        return returnValue;
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employee) {
        EmployeeDto returnValue = new EmployeeDto();

        EmployeeEntity employeeEntity = employeeRepository.findEmployeeEntityById(employeeId);

        if (employeeEntity == null)
            throw new EmployeeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        employeeEntity.setImage(employee.getImage());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setGender(employee.getGender());
        employeeEntity.setDateOfBirth(employee.getDateOfBirth());
        employeeEntity.setStatus(employee.getStatus());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setPhoneNumber(employee.getPhoneNumber());
        employeeEntity.setEncryptedPassword(employee.getEncryptedPassword());
        employeeEntity.setCreateDate(employee.getCreateDate());
        employeeEntity.setUpdateDate(employee.getUpdateDate());
        

        EmployeeEntity updatedEmployees = employeeRepository.save(employeeEntity);
        returnValue = new ModelMapper().map(updatedEmployees, EmployeeDto.class);

        return returnValue;
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findEmployeeEntityById(employeeId);

        if (employeeEntity == null)
            throw new EmployeeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        employeeRepository.delete(employeeEntity);
    }

    @Override
    public List<EmployeeDto> getEmployees(int page, int limit, String filter) {
        List<EmployeeDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<EmployeeEntity> employeePage = employeeRepository.filter(filter, pageableRequest);
        List<EmployeeEntity> employees = employeePage.getContent();

        for (EmployeeEntity employeeEntity : employees) {
            EmployeeDto employeeDto = new EmployeeDto();
            BeanUtils.copyProperties(employeeEntity, employeeDto);
            returnValue.add(employeeDto);
        }

        return returnValue;
    }

    @Override
    public Long count(String filter) {

        Long total = employeeRepository.count(filter);
        return total;
    }

    @Override
    public List<EmployeeDto> getEmployeeByEmployeeName(String employeeName, int page, int limit) {
        List<EmployeeDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<EmployeeEntity> employeePage = employeeRepository.findByLastNameContainingOrderByIdAsc(employeeName, pageableRequest);
        List<EmployeeEntity> employees = employeePage.getContent();

        for (EmployeeEntity employeeEntity : employees) {
            EmployeeDto employeeDto = new EmployeeDto();
            BeanUtils.copyProperties(employeeEntity, employeeDto);
            returnValue.add(employeeDto);
        }

        return returnValue;
    }
}
