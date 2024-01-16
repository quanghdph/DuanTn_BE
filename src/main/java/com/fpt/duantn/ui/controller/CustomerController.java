package com.fpt.duantn.ui.controller;

import com.fpt.duantn.io.entity.CustomerEntity;
import com.fpt.duantn.services.CustomerService;
import com.fpt.duantn.services.EmployeeService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.CustomerDto;
import com.fpt.duantn.ui.model.request.CustomerRequest;
import com.fpt.duantn.ui.model.response.*;
import com.fpt.duantn.util.FormErrorUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    Utils utils;

    @GetMapping(path = "/{id}")
    public CustomerRest getCustomer(@PathVariable Long id) {
        CustomerRest returnValue = new CustomerRest();

        CustomerDto customerDto = customerService.getCustomerById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(customerDto, CustomerRest.class);

        return returnValue;
    }

    @PostMapping()
    public CustomerRest createCustomer(@RequestBody CustomerRequest customerDetails) throws Exception {
        CustomerRest returnValue = new CustomerRest();

        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = modelMapper.map(customerDetails, CustomerDto.class);

        CustomerDto createdUser = customerService.createCustomer(customerDto);
        returnValue = modelMapper.map(createdUser, CustomerRest.class);

        return returnValue;
    }




    @PutMapping(path = "/{id}")
    public CustomerRest updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerDetails) {
        CustomerRest returnValue = new CustomerRest();

        CustomerDto customerDto = new CustomerDto();
        customerDto = new ModelMapper().map(customerDetails, CustomerDto.class);

        CustomerDto updateCustomer = customerService.updateCustomer(id, customerDto);
        returnValue = new ModelMapper().map(updateCustomer, CustomerRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteCustomer(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            customerService.deleteCustomer(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Khach Hang: Khach Hang có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Khach Hang: " + e.getMessage());
        }return returnValue;
    }

    @GetMapping("/phone-number")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @ResponseBody
    public CustomerResponse findByPhone(
            @RequestParam(value = "phoneNumber") Optional<String> phoneNumber
    ) {
        CustomerResponse customerReponse = customerService.findByPhoneNumber(phoneNumber.orElse(null));
        return customerReponse;
    }

    @GetMapping("/search")
    public List<CustomerRest> searchCustomers(@RequestParam(value = "key") String key,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "5") int limit) {
        List<CustomerRest> returnValue = new ArrayList<>();

        List<CustomerDto> customers =customerService.getCustomerByCustomerName(key, page, limit);

        for (CustomerDto customerDto : customers) {
            CustomerRest customerModel = new CustomerRest();
            BeanUtils.copyProperties(customerDto, customerModel);
            returnValue.add(customerModel);
        }

        return returnValue;
    }

    @GetMapping()
    public PaginationRest getCustomers(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "5") int limit,
                                       @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<CustomerRest> returnValue = new ArrayList<>();

        List<CustomerDto> customers = customerService.getCustomers(page, limit, filter);

        for (CustomerDto customerDto : customers) {
            CustomerRest customerModel = new CustomerRest();
            BeanUtils.copyProperties(customerDto, customerModel);
            returnValue.add(customerModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListCustomer(returnValue);
        paginationRest.setTotal(customerService.count(filter));

        return paginationRest;
    }

    @PostMapping ( "/fast")
    public ResponseEntity<?> addCustomerFast(@Valid @RequestBody CustomerEntity customer, BindingResult bindingResult) {
        if (bindingResult.getFieldError("phoneNumber")!=null||bindingResult.getFieldError("lastName")!=null||bindingResult.getFieldError("gender")!=null){
            Map<String, String> errors = FormErrorUtil.changeToMapError(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        if(!customer.getEmail().equals("")){
            if (customerService.findByEmail(customer.getEmail()).orElse(null)!=null||employeeService.findByEmail(customer.getEmail()).orElse(null)!=null){
                Map<String, String> errors = FormErrorUtil.changeToMapError(bindingResult);
                errors.put("email","Email đã tồn tại");
                return ResponseEntity.badRequest().body(errors);
            }
        }
        if (customerService.findByPhoneNumber(customer.getPhoneNumber())!=null||employeeService.findEByPhoneNumber(customer.getPhoneNumber()).isPresent()){
            Map<String, String> errors = FormErrorUtil.changeToMapError(bindingResult);
            errors.put("phoneNumber","Số điện thoại đã tồn tại");
            return ResponseEntity.badRequest().body(errors);
        }
        CustomerEntity customerSave = new CustomerEntity();
        customerSave.setFirstName(customer.getFirstName());
        customerSave.setLastName(customer.getLastName());
        customerSave.setPhoneNumber(customer.getPhoneNumber());
        customerSave.setGender(customer.getGender());
        String publicCustomerCode = utils.generateColorCode(8);
        customerSave.setCustomerCode(publicCustomerCode);
        if(!customer.getEmail().equals("")){
            customerSave.setEmail(customer.getEmail());
        }
        customerSave.setEmailVerificationStatus(true);
        CustomerEntity customerSaved = customerService.save(customerSave);
        customerSaved.setEmailVerificationToken(null);
        return ResponseEntity.ok(customerSaved);
    }
}
