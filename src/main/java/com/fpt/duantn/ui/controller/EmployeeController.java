package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.EmployeeService;
import com.fpt.duantn.shrared.dto.CRUD.EmployeeDto;
import com.fpt.duantn.ui.model.request.EmployeeDetailsRequestModel;
import com.fpt.duantn.ui.model.response.EmployeeRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(path = "/{id}")
    public EmployeeRest getEmployee(@PathVariable String id) {
        EmployeeRest returnValue = new EmployeeRest();

        EmployeeDto employeeDto = employeeService.getEmployeeByEmployeeCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(employeeDto, EmployeeRest.class);

        return returnValue;
    }

    @PostMapping()
    public EmployeeRest createEmployee(@RequestBody EmployeeDetailsRequestModel employeeDetails) throws Exception {
        EmployeeRest returnValue = new EmployeeRest();

        ModelMapper modelMapper = new ModelMapper();
        EmployeeDto employeeDto = modelMapper.map(employeeDetails, EmployeeDto.class);

        EmployeeDto createdUser = employeeService.createEmployee(employeeDto);
        returnValue = modelMapper.map(createdUser, EmployeeRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<EmployeeRest> getEmployees(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<EmployeeRest> returnValue = new ArrayList<>();

        List<EmployeeDto> employees = employeeService.getEmployees(page, limit);

        for (EmployeeDto employeeDto : employees) {
            EmployeeRest employeeModel = new EmployeeRest();
            BeanUtils.copyProperties(employeeDto, employeeModel);
            returnValue.add(employeeModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public EmployeeRest updateEmployee(@PathVariable String id, @RequestBody EmployeeDetailsRequestModel employeeDetails) {
        EmployeeRest returnValue = new EmployeeRest();

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto = new ModelMapper().map(employeeDetails, EmployeeDto.class);

        EmployeeDto updateEmployee = employeeService.updateEmployee(id, employeeDto);
        returnValue = new ModelMapper().map(updateEmployee, EmployeeRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteEmployee(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        employeeService.deleteEmployee(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<EmployeeRest> searchEmployees(@RequestParam(value = "employeeName") String employeeName,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<EmployeeRest> returnValue = new ArrayList<>();

        List<EmployeeDto> employees = employeeService.getEmployeeByEmployeeName(employeeName, page, limit);

        for (EmployeeDto employeeDto : employees) {
            EmployeeRest employeeModel = new EmployeeRest();
            BeanUtils.copyProperties(employeeDto, employeeModel);
            returnValue.add(employeeModel);
        }

        return returnValue;
    }

}
