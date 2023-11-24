package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.EmployeeCardService;
import com.fpt.duantn.shrared.dto.CRUD.EmployeeDto;
import com.fpt.duantn.ui.model.response.EmployeeCardRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employee-list")
public class EmployeeCardController {

    @Autowired
    EmployeeCardService employeeCardService;

    @GetMapping()
    public List<EmployeeCardRest> getCategory(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<EmployeeCardRest> returnValue = new ArrayList<>();

        List<EmployeeDto> employees = employeeCardService.getEmployeeCard(page, limit);

        for (EmployeeDto employeeDto : employees) {
            EmployeeCardRest employeeModel = new EmployeeCardRest();
            BeanUtils.copyProperties(employeeDto, employeeModel);
            returnValue.add(employeeModel);
        }

        return returnValue;

    }
}
