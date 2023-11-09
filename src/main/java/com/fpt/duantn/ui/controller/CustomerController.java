package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.CustomerService;
import com.fpt.duantn.shrared.dto.CRUD.CustomerDto;
import com.fpt.duantn.ui.model.request.CustomerDetailsRequestModel;
import com.fpt.duantn.ui.model.response.CustomerRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(path = "/{id}")
    public CustomerRest getCustomer(@PathVariable String id) {
        CustomerRest returnValue = new CustomerRest();

        CustomerDto customerDto = customerService.getCustomerByCustomerCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(customerDto, CustomerRest.class);

        return returnValue;
    }

    @PostMapping()
    public CustomerRest createCustomer(@RequestBody CustomerDetailsRequestModel customerDetails) throws Exception {
        CustomerRest returnValue = new CustomerRest();

        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = modelMapper.map(customerDetails, CustomerDto.class);

        CustomerDto createdUser = customerService.createCustomer(customerDto);
        returnValue = modelMapper.map(createdUser, CustomerRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<CustomerRest> getCustomers(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<CustomerRest> returnValue = new ArrayList<>();

        List<CustomerDto> customers = customerService.getCustomers(page, limit);

        for (CustomerDto customerDto : customers) {
            CustomerRest customerModel = new CustomerRest();
            BeanUtils.copyProperties(customerDto, customerModel);
            returnValue.add(customerModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public CustomerRest updateCustomer(@PathVariable String id, @RequestBody CustomerDetailsRequestModel customerDetails) {
        CustomerRest returnValue = new CustomerRest();

        CustomerDto customerDto = new CustomerDto();
        customerDto = new ModelMapper().map(customerDetails, CustomerDto.class);

        CustomerDto updateCustomer = customerService.updateCustomer(id, customerDto);
        returnValue = new ModelMapper().map(updateCustomer, CustomerRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteCustomer(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        customerService.deleteCustomer(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<CustomerRest> searchCustomers(@RequestParam(value = "customerName") String customerName,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<CustomerRest> returnValue = new ArrayList<>();

        List<CustomerDto> customers =customerService.getCustomerByCustomerName(customerName, page, limit);

        for (CustomerDto customerDto : customers) {
            CustomerRest customerModel = new CustomerRest();
            BeanUtils.copyProperties(customerDto, customerModel);
            returnValue.add(customerModel);
        }

        return returnValue;
    }

}
