package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.EmployeeService;
import com.fpt.duantn.shrared.dto.CRUD.EmployeeDto;
import com.fpt.duantn.ui.model.request.EmployeeRequest;
import com.fpt.duantn.ui.model.response.EmployeeRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(path = "/{id}")
    public EmployeeRest getEmployee(@PathVariable Long id) {
        EmployeeRest returnValue = new EmployeeRest();

        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(employeeDto, EmployeeRest.class);

        return returnValue;
    }

    @PostMapping()
    public EmployeeRest createEmployee(@RequestBody EmployeeRequest employeeDetails) throws Exception {
        EmployeeRest returnValue = new EmployeeRest();

        ModelMapper modelMapper = new ModelMapper();
        EmployeeDto employeeDto = modelMapper.map(employeeDetails, EmployeeDto.class);

        EmployeeDto createdUser = employeeService.createEmployee(employeeDto);
        returnValue = modelMapper.map(createdUser, EmployeeRest.class);

        return returnValue;
    }



    @PutMapping(path = "/{id}")
    public EmployeeRest updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeDetails) {
        EmployeeRest returnValue = new EmployeeRest();

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto = new ModelMapper().map(employeeDetails, EmployeeDto.class);
        try {
            EmployeeDto updateEmployee = employeeService.updateEmployee(id, employeeDto);
            returnValue = new ModelMapper().map(updateEmployee, EmployeeRest.class);
        }catch (Exception e) {
            System.out.println(e);
        }
        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteEmployee(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            employeeService.deleteEmployee(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Nhan Vien: Nhan Vien có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Nhan Vien: " + e.getMessage());
        }
        return returnValue;
    }



    @GetMapping("/search")
    public List<EmployeeRest> searchEmployees(@RequestParam(value = "employeeName") String employeeName,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "5") int limit) {
        List<EmployeeRest> returnValue = new ArrayList<>();

        List<EmployeeDto> employees = employeeService.getEmployeeByEmployeeName(employeeName, page, limit);

        for (EmployeeDto employeeDto : employees) {
            EmployeeRest employeeModel = new EmployeeRest();
            BeanUtils.copyProperties(employeeDto, employeeModel);
            returnValue.add(employeeModel);
        }

        return returnValue;
    }

    @GetMapping()
    public PaginationRest getEmployees(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "5") int limit,
                                       @RequestParam(value = "filter", defaultValue = "") String filter) {

        List<EmployeeRest> returnValue = new ArrayList<>();

        List<EmployeeDto> employees = employeeService.getEmployees(page, limit, filter);

        for (EmployeeDto employeeDto : employees) {
            EmployeeRest employeeModel = new EmployeeRest();
            BeanUtils.copyProperties(employeeDto, employeeModel);
            returnValue.add(employeeModel);
        }

        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListEmployees(returnValue);
        paginationRest.setTotal(employeeService.count(filter));


        return paginationRest;
    }


}
